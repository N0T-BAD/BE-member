package com.blockpage.memberservice.adaptor.infrastructure.message.async;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrapAddress}")
    private String bootStrapServer;

    @Value("${spring.kafka.interestTopic}")
    private String interestTopic;

    @Value("${spring.kafka.ratingTopic}")
    private String ratingTopic;

    @Value("${spring.kafka.commentTopic}")
    private String commentCount;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic newInterestTopic() {
        return new NewTopic(interestTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic newRatingTopic() {
        return new NewTopic(ratingTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic newCommentCount() {
        return new NewTopic(commentCount, 1, (short) 1);
    }
}
