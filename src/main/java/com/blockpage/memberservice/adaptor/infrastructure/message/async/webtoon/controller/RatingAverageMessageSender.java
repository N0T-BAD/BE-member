package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.RatingAverageMessage;
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
public class RatingAverageMessageSender {

    @Value("${spring.kafka.ratingTopic}")
    private String topicName;

    private final KafkaTemplate ratingKafkaTemplate;

    public void sendRatingAverage(RatingAverageMessage ratingAverageMessage){
        Message<RatingAverageMessage> message = MessageBuilder.withPayload(ratingAverageMessage)
            .setHeader(KafkaHeaders.TOPIC,topicName)
            .build();

        ListenableFuture<SendResult<String, RatingAverageMessage>> future = ratingKafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, RatingAverageMessage>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, RatingAverageMessage> result) {
                log.info("send message = " + result.getProducerRecord().value().toString() + "with offset = " + result.getRecordMetadata()
                    .offset());
            }
        });
    }

}