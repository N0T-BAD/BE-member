package com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.message.CommentCountMessage;
import com.blockpage.memberservice.application.port.out.port.CommentMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCountController implements CommentMessagePort {

    private final CommentCountMessageSender commentCountMessageSender;

    @Override
    public void sendCommentCount(CommentCountMessage commentCountMessage) {
        commentCountMessageSender.sendCommentCount(commentCountMessage);
    }
}