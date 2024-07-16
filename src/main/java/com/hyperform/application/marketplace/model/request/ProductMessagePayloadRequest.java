package com.hyperform.application.marketplace.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMessagePayloadRequest {

  private String requestId;
  private int page;
  private String category;
  private String subcategory;
}
