package com.hyperform.application.marketplace.model.response;

import lombok.Getter;

public class CachedResponse<T> {

  private static final long CACHE_TTL = 10 * 60 * 1000;

  @Getter
  private final T response;

  private final long timestamp;

  public CachedResponse(T response) {
    this.response = response;
    this.timestamp = System.currentTimeMillis();
  }

  public boolean isExpired() {
    return System.currentTimeMillis() - timestamp > CACHE_TTL;
  }

  public boolean isExpired(long currentTime) {
    return currentTime - timestamp > CACHE_TTL;
  }
}
