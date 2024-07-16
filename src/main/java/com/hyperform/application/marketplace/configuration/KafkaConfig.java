package com.hyperform.application.marketplace.configuration;

import com.hyperform.application.marketplace.util.MessageBrokerConstants;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

@EnableKafka
@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  //    @Value("${spring.kafka.properties.ssl.truststore.location}")
  //    private String truststoreLocation;
  //
  //    @Value("${spring.kafka.properties.ssl.truststore.password}")
  //    private String truststorePassword;
  //
  //    @Value("${spring.kafka.properties.ssl.keystore.location}")
  //    private String keystoreLocation;
  //
  //    @Value("${spring.kafka.properties.ssl.keystore.password}")
  //    private String keystorePassword;
  //
  //    @Value("${spring.kafka.properties.ssl.key.password}")
  //    private String keyPassword;

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //        configProps.put("security.protocol", "SSL");
    //        configProps.put("ssl.truststore.location", truststoreLocation);
    //        configProps.put("ssl.truststore.password", truststorePassword);
    //        configProps.put("ssl.keystore.location", keystoreLocation);
    //        configProps.put("ssl.keystore.password", keystorePassword);
    //        configProps.put("ssl.key.password", keyPassword);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ConsumerConfig.GROUP_ID_CONFIG, MessageBrokerConstants.FORM_MESSAGE_GROUP);
    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //        configProps.put("security.protocol", "SSL");
    //        configProps.put("ssl.truststore.location", truststoreLocation);
    //        configProps.put("ssl.truststore.password", truststorePassword);
    //        configProps.put("ssl.keystore.location", keystoreLocation);
    //        configProps.put("ssl.keystore.password", keystorePassword);
    //        configProps.put("ssl.key.password", keyPassword);
    return new DefaultKafkaConsumerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
