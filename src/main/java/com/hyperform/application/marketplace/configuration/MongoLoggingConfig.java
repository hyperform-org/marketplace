package com.hyperform.application.marketplace.configuration;

import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoLoggingConfig {

  private static final Logger logger = LoggerFactory.getLogger(MongoLoggingConfig.class);

  @Bean
  public CommandListener commandListener() {
    return new CommandListener() {
      @Override
      public void commandStarted(CommandStartedEvent event) {
        logger.debug("MongoDB command started: {}", event.getCommand());
      }

      @Override
      public void commandSucceeded(com.mongodb.event.CommandSucceededEvent event) {
        // You can log successful commands if needed
        logger.debug("MongoDB command successes: {}", event.getCommandName());
      }

      @Override
      public void commandFailed(com.mongodb.event.CommandFailedEvent event) {
        logger.error("MongoDB command failed: {}", event.getThrowable().getMessage());
      }
    };
  }
}
