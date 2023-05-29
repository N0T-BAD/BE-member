package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.InterestCountMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Component
@RequiredArgsConstructor
public class InterestCountMessageSender {

    @Value("${spring.kafka.interestTopic}")
    private String topicName;

    private final KafkaTemplate kafkaTemplate;

    public void sendInterestCount(InterestCountMessage interestCountMessage) {
        Message<InterestCountMessage> message = MessageBuilder.withPayload(interestCountMessage)
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .build();

        ListenableFuture<SendResult<String, InterestCountMessage>> future = kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, InterestCountMessage>>() {
            @Override
            public void onSuccess(SendResult<String, InterestCountMessage> result) {
                log.info("send message = " + result.getProducerRecord().value().toString() + " with offset = " + result.getRecordMetadata()
                    .offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }
        });
    }
}
