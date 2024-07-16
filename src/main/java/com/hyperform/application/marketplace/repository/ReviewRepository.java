package com.hyperform.application.marketplace.repository;

import com.hyperform.application.marketplace.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
  Flux<Review> findByProductId(String productId);
}
