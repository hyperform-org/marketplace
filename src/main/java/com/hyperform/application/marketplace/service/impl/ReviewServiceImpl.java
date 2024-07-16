package com.hyperform.application.marketplace.service.impl;

import com.hyperform.application.marketplace.domain.Review;
import com.hyperform.application.marketplace.model.request.ReviewRequest;
import com.hyperform.application.marketplace.repository.ReviewRepository;
import com.hyperform.application.marketplace.service.ReviewService;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Override
  public Mono<Review> createReview(Mono<ReviewRequest> reviewRequestMono) {
    return reviewRequestMono.flatMap(reviewRequest -> {
      Review review = new Review();
      review.setProductId(reviewRequest.getProductId());
      review.setUserId(reviewRequest.getUserId());
      review.setComment(reviewRequest.getComment());
      review.setRating(reviewRequest.getRating());
      review.setCreatedDate(new Date());
      return reviewRepository.save(review);
    });
  }

  @Override
  public Mono<Review> getReviewById(String id) {
    return reviewRepository.findById(id);
  }

  @Override
  public Flux<Review> getReviewsByProductId(String productId) {
    return reviewRepository.findByProductId(productId);
  }

  @Override
  public Mono<Void> deleteReview(String id) {
    return reviewRepository.deleteById(id);
  }
}
