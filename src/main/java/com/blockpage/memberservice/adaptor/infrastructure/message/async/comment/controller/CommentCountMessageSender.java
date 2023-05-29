package com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.message.CommentCountMessage;
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
public class CommentCountMessageSender {

    @Value("${spring.kafka.commentTopic}")
    private String topicName;

    private final KafkaTemplate kafkaTemplate;

    public void sendCommentCount(CommentCountMessage commentCountMessage) {
        Message<CommentCountMessage> message = MessageBuilder.withPayload(commentCountMessage)
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .build();

        ListenableFuture<SendResult<String, CommentCountMessage>> future = kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, CommentCountMessage>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, CommentCountMessage> result) {
                log.info("send message = " + result.getProducerRecord().value().toString() + "with offset = " + result.getRecordMetadata()
                    .offset());
            }
        });
    }
}