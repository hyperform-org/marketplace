package com.hyperform.application.marketplace.service.impl;

import com.hyperform.application.marketplace.domain.ReviewVote;
import com.hyperform.application.marketplace.model.request.ReviewVoteRequest;
import com.hyperform.application.marketplace.repository.ReviewVoteRepository;
import com.hyperform.application.marketplace.service.ReviewVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewVoteServiceImpl implements ReviewVoteService {

  @Autowired
  private ReviewVoteRepository reviewVoteRepository;

  @Override
  public Mono<ReviewVote> createReviewVote(Mono<ReviewVoteRequest> reviewVoteRequestMono) {
    return reviewVoteRequestMono.flatMap(reviewVoteRequest -> {
      ReviewVote reviewVote = new ReviewVote();
      reviewVote.setReviewId(reviewVoteRequest.getReviewId());
      reviewVote.setUserId(reviewVoteRequest.getUserId());
      reviewVote.setUpvote(reviewVoteRequest.getUpvote());
      return reviewVoteRepository.save(reviewVote);
    });
  }

  @Override
  public Flux<ReviewVote> getVotesByReviewId(String reviewId) {
    return reviewVoteRepository.findByReviewId(reviewId);
  }

  @Override
  public Mono<Void> deleteReviewVote(String id) {
    return reviewVoteRepository.deleteById(id);
  }
}
