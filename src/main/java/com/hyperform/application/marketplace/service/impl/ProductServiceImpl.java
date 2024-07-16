package com.hyperform.application.marketplace.service.impl;

import com.hyperform.application.marketplace.domain.Product;
import com.hyperform.application.marketplace.enums.MarketPlaceItemEnum;
import com.hyperform.application.marketplace.exceptions.MarketPlaceServiceException;
import com.hyperform.application.marketplace.exceptions.NotFoundException;
import com.hyperform.application.marketplace.mapper.ProductMapper;
import com.hyperform.application.marketplace.model.dto.ProductDto;
import com.hyperform.application.marketplace.model.request.ProductMessagePayloadRequest;
import com.hyperform.application.marketplace.model.request.ProductRequest;
import com.hyperform.application.marketplace.model.request.UpdateProductRequest;
import com.hyperform.application.marketplace.model.response.MarketplaceItemResponse;
import com.hyperform.application.marketplace.model.vo.CustomFile;
import com.hyperform.application.marketplace.repository.ProductRepository;
import com.hyperform.application.marketplace.service.ProductService;
import com.hyperform.application.marketplace.service.StorageService;
import com.hyperform.application.marketplace.util.Utils;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Autowired
  private StorageService storageService;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;

  @Override
  public Flux<ProductDto> getProductByOwner(String ownerId, Integer status) {
    return productRepository
      .findBySellerAndStatus(ownerId, status)
      .collectList()
      .flatMapMany(workspaces -> {
        if (workspaces.isEmpty()) {
          return Flux.empty();
        } else {
          return Flux.fromIterable(workspaces).map(productMapper::documentToDto);
        }
      })
      .doOnNext(dto -> logger.info("Found Product: " + dto))
      .switchIfEmpty(Mono.fromRunnable(() -> logger.info("No workspaces found")));
  }

  @Override
  public Mono<ProductDto> getProductById(String id) {
    return productRepository.findById(id).map(productMapper::documentToDto);
  }

  @Override
  public Mono<ProductDto> createProduct(Mono<ProductRequest> workspaceRequestMono) {
    return workspaceRequestMono
      .map(productMapper::reqToDto)
      .map(productMapper::dtoToDocument)
      .flatMap(product -> {
        product.setStatus(MarketPlaceItemEnum.SELLING.getKey());
        product.setCreatedDate(new Date());
        product.setUpdatedDate(new Date());
        product.setCreatedBy(product.getSeller());
        return productRepository.save(product);
      })
      .flatMap(savedProduct -> {
        return workspaceRequestMono.flatMap(productRequest -> {
          CustomFile[] images = productRequest.getImages();
          List<String> imageUrls = new ArrayList<>();
          if (images != null && images.length > 0) {
            for (CustomFile image : images) {
              if (image != null && !StringUtils.isEmpty(image.getFile())) {
                try {
                  String uuid = UUID.randomUUID().toString();
                  String fileExtension = Utils.getFileExtension(image.getFileName());
                  String imageFileName = uuid + "_marketplace." + fileExtension; // Ensure unique filenames
                  storageService.storeImage(image.getFile(), imageFileName);
                  imageUrls.add(imageFileName); // Store the filename or URL as required
                } catch (Exception e) {
                  logger.error("Failed to store file: {}", image.getFileName(), e);
                }
              }
            }
          }
          savedProduct.setImageUrls(imageUrls);
          return productRepository.save(savedProduct).map(productMapper::documentToDto);
        });
      });
  }

  @Override
  public Mono<ProductDto> updateProduct(String id, UpdateProductRequest body) {
    return productRepository
      .findById(id)
      .map(foundBeer -> {
        foundBeer.setTitle(body.getTitle());
        foundBeer.setDescription(body.getDescription());
        foundBeer.setUpdatedDate(new Date());
        return foundBeer;
      })
      .flatMap(productRepository::save)
      .map(productMapper::documentToDto);
  }

  @Override
  public Mono<Void> deleteProduct(String id, String createdBy) {
    return productRepository
      .findById(id)
      .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + id)))
      .flatMap(existingProduct -> {
        //                  if (!existingProduct.getCreatedBy().equalsIgnoreCase(createdBy)) {
        //                      return Mono.error(new UnauthorizedException("You are not authorized to delete this workspace"));
        //                  }
        return productRepository.delete(existingProduct).then(productRepository.deleteById(id));
      });
  }

  @Override
  public Mono<ProductDto> patchProduct(String id, ProductDto workspaceDto) {
    return productRepository
      .findById(id)
      .map(foundBeer -> {
        if (StringUtils.hasText(workspaceDto.getTitle())) {
          foundBeer.setTitle(workspaceDto.getTitle());
        }

        if (StringUtils.hasText(workspaceDto.getDescription())) {
          foundBeer.setDescription(workspaceDto.getDescription());
        }
        return foundBeer;
      })
      .flatMap(productRepository::save)
      .map(productMapper::documentToDto);
  }

  @Override
  public Flux<ProductDto> findProducts(String query, String status, String createdFrom, String createdTo, String owner, Pageable pageable) {
    String titlePattern = query != null ? "^" + query : ".*";
    String descriptionPattern = query != null ? ".*" + query + ".*" : ".*";
    String ownerPattern = owner != null ? "^" + owner + ".*" : ".*";
    Date createdFromDate = Utils.parseDate(createdFrom);
    Date createdToDate = Utils.parseDate(createdTo);
    return productRepository
      .findAllByCustomQuery(titlePattern, descriptionPattern, createdFromDate, createdToDate, ownerPattern, pageable)
      .map(productMapper::documentToDto)
      .doOnNext(dto -> logger.debug("Found Product: " + dto))
      .switchIfEmpty(Mono.fromRunnable(() -> logger.debug("No workspaces found")));
  }

  @Override
  public Flux<ProductDto> getBoughtProducts(String userId) {
    return productRepository.findByBuyer(userId).map(productMapper::documentToDto);
  }

  @Override
  public Flux<ProductDto> loadProducts(ProductMessagePayloadRequest payload) {
    String category = payload.getCategory() != null ? payload.getCategory() : "";
    String subcategory = payload.getSubcategory() != null ? payload.getSubcategory() : "";

    Pageable pageable = PageRequest.of(payload.getPage(), 100);

    if (category != null && !category.isEmpty() && subcategory != null && !subcategory.isEmpty()) {
      return productRepository.findByCategoryAndSubcategory(category, subcategory, pageable).map(productMapper::documentToDto);
    } else if (category != null && !category.isEmpty()) {
      return productRepository.findByCategory(category, pageable).map(productMapper::documentToDto);
    } else if (subcategory != null && !subcategory.isEmpty()) {
      return productRepository.findBySubcategory(subcategory, pageable).map(productMapper::documentToDto);
    } else {
      return productRepository.findAllBy(pageable).map(productMapper::documentToDto);
    }
  }

  @Override
  public Flux<ProductDto> getUpdates(ProductMessagePayloadRequest payload) {
    String category = payload.getCategory() != null ? payload.getCategory() : "";
    String subcategory = payload.getSubcategory() != null ? payload.getSubcategory() : "";

    return productRepository.findByCategoryAndSubcategory(category, subcategory, PageRequest.of(payload.getPage(), 10)).map(productMapper::documentToDto);
  }

  @Override
  public MarketplaceItemResponse processFormAnalysis(ProductMessagePayloadRequest request) {
    Flux<ProductDto> products = loadProducts(request);
    List<ProductDto> productList = products.collectList().blockOptional().orElse(Collections.emptyList());
    MarketplaceItemResponse response = new MarketplaceItemResponse();
    response.setRequestId(request.getRequestId());
    response.setItems(productList);

    return response;
  }
}
