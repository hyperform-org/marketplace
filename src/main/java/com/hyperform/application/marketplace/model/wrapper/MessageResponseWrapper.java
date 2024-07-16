package com.hyperform.application.marketplace.model.wrapper;

public class MessageResponseWrapper<T> {

  private final String requestId;
  private final T response;

  public MessageResponseWrapper(String requestId, T response) {
    this.requestId = requestId;
    this.response = response;
  }

  public String getRequestId() {
    return requestId;
  }

  public T getResponse() {
    return response;
  }
}
