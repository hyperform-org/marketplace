package com.hyperform.application.marketplace.model.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class BaseErrorResponse {

  private Map<String, String> attributes;
  private Map<String, String> server;

  public BaseErrorResponse() {
    this.attributes = new HashMap<>();
    this.server = new HashMap<>();
  }

  public BaseErrorResponse(Map<String, String> attributes) {
    this.attributes = attributes;
    this.server = new HashMap<>();
  }

  public BaseErrorResponse(Map<String, String> attributes, Map<String, String> server) {
    if (attributes == null) {
      this.attributes = new HashMap<>();
    } else {
      this.attributes = attributes;
    }

    this.server = server;
  }
}
