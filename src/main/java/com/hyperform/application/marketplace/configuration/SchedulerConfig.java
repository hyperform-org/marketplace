package com.hyperform.application.marketplace.configuration;

import com.hyperform.application.marketplace.model.response.CachedResponse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

  @Bean
  public ConcurrentMap<String, CachedResponse<?>> responseCache() {
    return new ConcurrentHashMap<>();
  }
}
