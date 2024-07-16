package com.hyperform.application.marketplace.configuration;

import com.mongodb.event.CommandListener;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoClientSettingsConfig {

  private final CommandListener commandListener;

  public MongoClientSettingsConfig(CommandListener commandListener) {
    this.commandListener = commandListener;
  }

  @Bean
  public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
    return builder -> builder.addCommandListener(commandListener);
  }
}
