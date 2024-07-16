package com.hyperform.application.marketplace.model.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class ReviewResponse extends BaseResponse {

  private String productId;

  private String userId;

  private String comment;

  private int rating;
}
