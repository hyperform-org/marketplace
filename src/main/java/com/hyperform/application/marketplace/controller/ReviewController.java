package com.hyperform.application.marketplace.controller;

import com.hyperform.application.marketplace.domain.Review;
import com.hyperform.application.marketplace.model.request.ReviewRequest;
import com.hyperform.application.marketplace.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Review", description = "Review management APIs")
@RestController
@RequestMapping(value = "api/marketplace/v1/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {

  private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

  @Autowired
  private ReviewService reviewService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Review> createReview(@Valid @RequestBody Mono<ReviewRequest> reviewRequestMono) {
    return reviewService.createReview(reviewRequestMono);
  }

  @GetMapping("/{id}")
  public Mono<Review> getReviewById(@PathVariable String id) {
    return reviewService.getReviewById(id);
  }

  @GetMapping("/product/{productId}")
  public Flux<Review> getReviewsByProductId(@PathVariable String productId) {
    return reviewService.getReviewsByProductId(productId);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteReview(@PathVariable String id) {
    return reviewService.deleteReview(id);
  }
}
