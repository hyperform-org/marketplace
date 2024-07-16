package com.hyperform.application.marketplace.controller;

import com.hyperform.application.marketplace.domain.ReviewVote;
import com.hyperform.application.marketplace.model.request.ReviewVoteRequest;
import com.hyperform.application.marketplace.service.ReviewVoteService;
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

@Tag(name = "ReviewVote", description = "Review Vote management APIs")
@RestController
@RequestMapping(value = "api/marketplace/v1/review-vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewVoteController {

  private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

  @Autowired
  private ReviewVoteService reviewVoteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ReviewVote> createReviewVote(@Valid @RequestBody Mono<ReviewVoteRequest> reviewVoteRequestMono) {
    return reviewVoteService.createReviewVote(reviewVoteRequestMono);
  }

  @GetMapping("/review/{reviewId}")
  public Flux<ReviewVote> getVotesByReviewId(@PathVariable String reviewId) {
    return reviewVoteService.getVotesByReviewId(reviewId);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteReviewVote(@PathVariable String id) {
    return reviewVoteService.deleteReviewVote(id);
  }
}
