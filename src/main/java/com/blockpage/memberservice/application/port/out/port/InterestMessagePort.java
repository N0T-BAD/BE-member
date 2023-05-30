package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.InterestCountMessage;

public interface InterestMessagePort {

    void sendInterestCount(InterestCountMessage interestCountMessage);
}
