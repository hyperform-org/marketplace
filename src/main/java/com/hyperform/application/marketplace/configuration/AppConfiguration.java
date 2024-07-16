package com.hyperform.application.marketplace.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfiguration {

  public String clientBaseUrl;

  @Value("${app.client.base-url}")
  public void setClientBaseUrl(String clientBaseUrl) {
    this.clientBaseUrl = clientBaseUrl;
  }
}
