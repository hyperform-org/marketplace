package com.hyperform.application.marketplace.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReviewVoteRequest {

  @NotEmpty(message = "Review ID is required")
  private String reviewId;

  @NotEmpty(message = "User ID is required")
  private String userId;

  @NotNull(message = "Vote is required")
  private Boolean upvote;
}
