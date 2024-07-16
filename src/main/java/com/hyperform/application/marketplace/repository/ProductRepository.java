package com.hyperform.application.marketplace.repository;

import com.hyperform.application.marketplace.domain.Product;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
  Mono<Product> findFirstByTitle(String title);
  Flux<Product> findBySellerAndStatus(String userId, Integer status);
  Flux<Product> findByBuyer(String userId);

  Flux<Product> findByCategoryAndSubcategory(String category, String subcategory, Pageable pageable);

  Flux<Product> findByCategory(String category, Pageable pageable);

  Flux<Product> findBySubcategory(String subcategory, Pageable pageable);

  Flux<Product> findAllBy(Pageable pageable);

  @Query(
    "{" +
    "'$and': [" +
    "{ 'title': { $regex: ?0, $options: 'i' } }," +
    "{ 'description': { $regex: ?1, $options: 'i' } }," +
    "{ $or: [ { 'created_date': { $gte: ?2 } }, { 'created_date': { $exists: false } } ] }," +
    "{ $or: [ { 'created_date': { $lte: ?3 } }, { 'created_date': { $exists: false } } ] }," +
    "{ 'created_by': { $regex: ?4, $options: 'i' } }," +
    "]}"
  )
  Flux<Product> findAllByCustomQuery(String titlePattern, String descriptionPattern, Date createdFrom, Date createdTo, String createdBy, Pageable pageable);
}
