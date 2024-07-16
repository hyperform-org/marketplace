package com.hyperform.application.marketplace.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class ReviewVoteResponse extends BaseResponse {

  private String reviewId;
  private String userId;
  private boolean upvote;
}
