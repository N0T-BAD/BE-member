package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.EmotionEntity;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.DeleteQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.PostQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Emotion {

    private Long id;

    private String memberEmail;

    private Long episodeId;

    private Long commentId;

    private Boolean choice;

    private Boolean emotion;

    public Emotion(Long commentId, Boolean emotion) {
        this.commentId = commentId;
        this.emotion = emotion;
    }

    public Emotion(Boolean choice, Boolean emotion) {
        this.choice = choice;
        this.emotion = emotion;

    }

    public static Emotion postEmotion(PostQuery postQuery) {
        return Emotion.builder()
            .memberEmail(postQuery.getMemberEmail())
            .episodeId(postQuery.getEpisodeId())
            .commentId(postQuery.getCommentId())
            .emotion(postQuery.getEmotion())
            .build();
    }

    public static Emotion findEmotion(FindQuery findQuery) {
        return Emotion.builder()
            .memberEmail(findQuery.getMemberEmail())
            .commentId(findQuery.getCommentId())
            .build();
    }

    public static Emotion fromEntity(EmotionEntity emotionEntity) {
        return Emotion.builder()
            .id(emotionEntity.getId())
            .commentId(emotionEntity.getCommentId())
            .emotion(emotionEntity.getEmotion())
            .build();
    }

    public static Emotion deleteEmotion(DeleteQuery deleteQuery) {
        return Emotion.builder().id(deleteQuery.getId()).build();
    }

}