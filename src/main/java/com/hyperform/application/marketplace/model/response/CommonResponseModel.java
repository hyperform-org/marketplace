package com.hyperform.application.marketplace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommonResponseModel {

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommonStatus {

    private Integer status;
  }
}
