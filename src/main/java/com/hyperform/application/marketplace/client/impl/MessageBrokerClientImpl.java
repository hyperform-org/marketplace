package com.hyperform.application.marketplace.client.impl;


import com.hyperform.application.marketplace.client.MessageBrokerClient;
import com.hyperform.application.marketplace.model.wrapper.MessageResponseWrapper;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class MessageBrokerClientImpl implements MessageBrokerClient {

  private final Logger logger = LoggerFactory.getLogger(MessageBrokerClientImpl.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private final ConcurrentMap<String, Sinks.Many<MessageResponseWrapper<?>>> sinkMap = new ConcurrentHashMap<>();

}
