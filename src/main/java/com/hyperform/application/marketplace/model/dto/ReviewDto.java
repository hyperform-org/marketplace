package com.hyperform.application.marketplace.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDto extends AbstractDto {

  private String productId;

  private String userId;

  private String comment;

  private int rating;
}
