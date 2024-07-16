package com.hyperform.application.marketplace.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewVoteDto extends AbstractDto {

  private String reviewId;
  private String userId;
  private boolean upvote;
}
