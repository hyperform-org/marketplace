package com.hyperform.application.marketplace.controller;

import com.hyperform.application.marketplace.mapper.ProductMapper;
import com.hyperform.application.marketplace.model.dto.ProductDto;
import com.hyperform.application.marketplace.model.request.ProductRequest;
import com.hyperform.application.marketplace.model.request.UpdateProductRequest;
import com.hyperform.application.marketplace.model.response.ProductResponse;
import com.hyperform.application.marketplace.service.ProductService;
import com.hyperform.application.marketplace.util.Utils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Products", description = "Products management APIs")
@RestController
@RequestMapping(value = "api/marketplace/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

  private final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductMapper productMapper;

  @GetMapping("/{id}")
  public Mono<ProductResponse> getProductById(@PathVariable String id) {
    return productService.getProductById(id).map(productMapper::dtoToRes);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Mono<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
    logger.info("Directly received ProductRequest: {}", productRequest);
    return productService.createProduct(Mono.just(productRequest)).map(productMapper::dtoToRes);
  }

  @PutMapping("/{id}")
  public Mono<ProductResponse> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest productRequest) {
    return productService.updateProduct(id, productRequest).map(productMapper::dtoToRes);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteWorkspace(@PathVariable String id, String createdBy) {
    return productService.deleteProduct(id, createdBy).then(Mono.empty());
  }

  @PatchMapping("/{id}")
  public Mono<ProductDto> patchWorkspace(@PathVariable String id, @RequestBody ProductDto workspaceDto) {
    return productService.patchProduct(id, workspaceDto);
  }

  @GetMapping("")
  public Flux<ProductDto> findProduct(
    @RequestParam(required = false) String query,
    @RequestParam(defaultValue = "status") String status,
    @RequestParam(required = false, defaultValue = "2020-01-01T00:00:00Z") String createdFrom,
    @RequestParam(required = false, defaultValue = "2100-10-31T06:20:00Z") String createdTo,
    @RequestParam(required = false) String owner,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "createdDate:desc") String[] sort
  ) {
    logger.debug("Rest request to search Product");
    return productService
      .findProducts(query, status, createdFrom, createdTo, owner, PageRequest.of(page, size, Utils.byOrder(sort)))
      .doOnError(error -> {
        logger.error("Error in controller while fetching workspaces", error);
      })
      .onErrorResume(error -> Flux.empty());
  }

  @GetMapping("/{userId}/{status}")
  public Flux<ProductDto> getSellingItems(@PathVariable String userId, @PathVariable Integer status) {
    Flux<ProductDto> items = productService.getProductByOwner(userId, status);
    return items;
  }

  @GetMapping("/bought/{userId}")
  public Flux<ProductDto> getBoughtItems(@PathVariable String userId) {
    Flux<ProductDto> items = productService.getBoughtProducts(userId);
    return items;
  }
}
