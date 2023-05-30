package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.RatingAverageMessage;
import com.blockpage.memberservice.application.port.out.port.RatingMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingMessageController implements RatingMessagePort {

    private final RatingAverageMessageSender ratingAverageMessageSender;

    @Override
    public void sendRatingAverage(RatingAverageMessage ratingAverageMessage) {
        ratingAverageMessageSender.sendRatingAverage(ratingAverageMessage);
    }
}