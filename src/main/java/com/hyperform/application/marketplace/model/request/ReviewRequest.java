package com.hyperform.application.marketplace.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReviewRequest {

  @NotEmpty(message = "Product ID is required")
  private String productId;

  @NotEmpty(message = "User ID is required")
  private String userId;

  @NotEmpty(message = "Comment is required")
  private String comment;

  @NotNull(message = "Rating is required")
  @Min(value = 1, message = "Rating must be at least 1")
  @Max(value = 5, message = "Rating must be at most 5")
  private int rating;
}
