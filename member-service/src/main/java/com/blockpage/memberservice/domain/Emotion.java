package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.DeleteQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.SaveQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.FindQuery;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Emotion {

    private Long id;

    private MemberEntity memberEntity;

    private Long episodeId;

    private Long commentId;

    private Boolean emotion;

    public static Emotion saveEmotion(SaveQuery saveQuery, MemberEntity memberEntity){
        return Emotion.builder()
            .memberEntity(memberEntity)
            .episodeId(saveQuery.getEpisodeId())
            .commentId(saveQuery.getCommentId())
            .emotion(saveQuery.getEmotion())
            .build();
    }

    public Emotion(FindQuery findQuery){
        this.episodeId = findQuery.getEpisodeId();
    }

    public static Emotion findEmotion(EmotionDto emotionDto){
        return Emotion.builder()
            .id(emotionDto.getId())
            .commentId(emotionDto.getCommentId())
            .emotion(emotionDto.getEmotion())
            .build();
    }

    public static Emotion deleteEmotion(DeleteQuery deleteQuery){
        return Emotion.builder().id(deleteQuery.getId()).build();
    }

}