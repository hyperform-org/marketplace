package com.hyperform.application.marketplace.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperform.application.marketplace.exceptions.MarketPlaceServiceException;
//import com.hyperform.application.marketplace.model.request.StatisticsFormRequest;
import com.hyperform.application.marketplace.model.request.ProductMessagePayloadRequest;
import com.hyperform.application.marketplace.model.response.BaseResponse;
import com.hyperform.application.marketplace.model.response.CachedResponse;
import com.hyperform.application.marketplace.model.response.MarketplaceItemResponse;
//import com.hyperform.application.marketplace.service.FormStatisticsService;
import com.hyperform.application.marketplace.service.ProductService;
import com.hyperform.application.marketplace.util.MessageBrokerConstants;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

  private final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ProductService productService;

  @Autowired
  private ConcurrentMap<String, CachedResponse<?>> responseCache;

  @KafkaListener(topics = MessageBrokerConstants.MARKETPLACE_REQUEST, groupId = MessageBrokerConstants.FORM_MESSAGE_GROUP)
  public void handleRequestGetFormAnalysisById(String message) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ProductMessagePayloadRequest request = objectMapper.readValue(message, ProductMessagePayloadRequest.class);
      String requestId = request.getRequestId();

      try {
        MarketplaceItemResponse response = productService.processFormAnalysis(request);
        response.setRequestId(requestId);

        String responseMessage = new ObjectMapper().writeValueAsString(response);
        logger.info("Response: {}", responseMessage);
        kafkaTemplate.send(MessageBrokerConstants.MARKETPLACE_RESPONSE, new ObjectMapper().writeValueAsString(response));
      } catch (MarketPlaceServiceException e) {
        BaseResponse errorResponse = new BaseResponse();
        errorResponse.setRequestId(requestId);
        errorResponse.setError("Market error: {} " + e);

        String errorMessage = new ObjectMapper().writeValueAsString(errorResponse);
        logger.error("Error Response: {}", errorMessage);

        kafkaTemplate.send(MessageBrokerConstants.MARKETPLACE_RESPONSE, new ObjectMapper().writeValueAsString(errorResponse));
      }
    } catch (Exception e) {
      logger.error("Error Handle Request Get Form Analysis By Id: ", e);
    }
  }
}
