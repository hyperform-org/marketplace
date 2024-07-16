package com.hyperform.application.marketplace.service;

import com.hyperform.application.marketplace.model.dto.ProductDto;
import com.hyperform.application.marketplace.model.request.ProductMessagePayloadRequest;
import com.hyperform.application.marketplace.model.request.ProductRequest;
import com.hyperform.application.marketplace.model.request.UpdateProductRequest;
import com.hyperform.application.marketplace.model.response.MarketplaceItemResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
  Flux<ProductDto> getProductByOwner(String ownerId, Integer status);

  public Mono<ProductDto> getProductById(String id);

  public Mono<ProductDto> createProduct(Mono<ProductRequest> workspaceDtoMono);

  public Mono<ProductDto> updateProduct(String id, UpdateProductRequest body);

  public Mono<Void> deleteProduct(String id, String createdBy);

  public Mono<ProductDto> patchProduct(String id, ProductDto workspaceDto);

  public Flux<ProductDto> findProducts(String query, String status, String createdFrom, String createdTo, String owner, Pageable pageable);

  Flux<ProductDto> getBoughtProducts(String userId);

  Flux<ProductDto> loadProducts(ProductMessagePayloadRequest payload);
  Flux<ProductDto> getUpdates(ProductMessagePayloadRequest payload);

  public MarketplaceItemResponse processFormAnalysis(ProductMessagePayloadRequest request);
}
