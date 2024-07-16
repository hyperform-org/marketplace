package com.hyperform.application.marketplace.service;

import com.hyperform.application.marketplace.domain.Review;
import com.hyperform.application.marketplace.model.request.ReviewRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {
  public Mono<Review> createReview(Mono<ReviewRequest> reviewRequestMono);

  public Mono<Review> getReviewById(String id);

  public Flux<Review> getReviewsByProductId(String productId);

  public Mono<Void> deleteReview(String id);
}
