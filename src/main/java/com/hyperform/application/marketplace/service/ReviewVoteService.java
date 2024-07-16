package com.hyperform.application.marketplace.service;

import com.hyperform.application.marketplace.domain.ReviewVote;
import com.hyperform.application.marketplace.model.request.ReviewVoteRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewVoteService {
  public Mono<ReviewVote> createReviewVote(Mono<ReviewVoteRequest> reviewVoteRequestMono);

  public Flux<ReviewVote> getVotesByReviewId(String reviewId);

  public Mono<Void> deleteReviewVote(String id);
}
