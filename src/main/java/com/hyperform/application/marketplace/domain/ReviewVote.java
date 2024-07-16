package com.hyperform.application.marketplace.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "Marketplace_Reviews_Votes")
public class ReviewVote extends AbstractDocument {

  private static final long serialVersionUID = 5957089583837151111L;

  @NotNull(message = "Review Id is required")
  @Builder.Default
  @Field("review_id")
  private String reviewId = "";

  @NotNull(message = "User Id is required")
  @Builder.Default
  @Field("user_id")
  private String userId = "";

  @NotNull(message = "Upvote Id is required")
  @Builder.Default
  @Field("upvote")
  private boolean upvote = false;
}
