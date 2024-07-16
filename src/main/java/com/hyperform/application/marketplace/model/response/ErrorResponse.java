package com.hyperform.application.marketplace.model.response;

import lombok.Data;

@Data
public class ErrorResponse {

  private String requestId;
  private String error;
}
