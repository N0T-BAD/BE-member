package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.InterestCountMessage;
import com.blockpage.memberservice.application.port.out.port.InterestMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InterestMessageController implements InterestMessagePort {

    private final InterestCountMessageSender interestCountMessageSender;

    @Override
    public void sendInterestCount(InterestCountMessage interestCountMessage) {
        interestCountMessageSender.sendInterestCount(interestCountMessage);
    }
}
