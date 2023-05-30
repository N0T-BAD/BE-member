package com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCountMessage {

    private Long commentId;

    private Integer likesCount;

    private Integer disLikesCount;

    public static CommentCountMessage sendMessage(Long commentId, Integer likesCount, Integer disLikesCount) {
        return CommentCountMessage.builder()
            .commentId(commentId)
            .likesCount(likesCount)
            .disLikesCount(disLikesCount)
            .build();
    }
}