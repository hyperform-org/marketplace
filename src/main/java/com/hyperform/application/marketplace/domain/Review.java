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
@Document(collection = "Marketplace_Reviews")
public class Review extends AbstractDocument {

  private static final long serialVersionUID = 7624500124740428946L;

  @NotNull(message = "Product Id is required")
  @Builder.Default
  @Field("product_id")
  private String productId = "";

  @NotNull(message = "User Id is required")
  @Builder.Default
  @Field("user_id")
  private String userId = "";

  @NotNull(message = "Comment is required")
  @Builder.Default
  private String comment = "";

  @NotNull(message = "Rating is required")
  @Builder.Default
  private int rating = 0;
}
