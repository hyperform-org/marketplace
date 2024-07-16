package com.hyperform.application.marketplace.repository;

import com.hyperform.application.marketplace.domain.ReviewVote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewVoteRepository extends ReactiveMongoRepository<ReviewVote, String> {
  Flux<ReviewVote> findByReviewId(String reviewId);
}
