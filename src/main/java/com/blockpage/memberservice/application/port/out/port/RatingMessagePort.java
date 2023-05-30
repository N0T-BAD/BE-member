package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.RatingAverageMessage;

public interface RatingMessagePort {

    void sendRatingAverage(RatingAverageMessage ratingAverageMessage);
}