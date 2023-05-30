package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.configuration;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.InterestCountMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class InterestCountProducerConfig {

    @Value("${spring.kafka.bootstrapAddress}")
    private String bootStrapServer;

    @Bean
    public ProducerFactory<String, InterestCountMessage> interestProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, InterestCountMessage> interestKafkaTemplate() {
        return new KafkaTemplate<>(interestProducerFactory());
    }
}
