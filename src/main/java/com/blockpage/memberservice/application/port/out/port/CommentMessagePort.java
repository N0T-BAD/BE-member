package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.message.CommentCountMessage;

public interface CommentMessagePort {

    void sendCommentCount(CommentCountMessage commentCountMessage);
}