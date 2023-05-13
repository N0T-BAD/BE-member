package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.entity.EmotionEntity;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.SaveQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmotionDto {

    private Long id;

    private Long episodeId;

    private Long commentId;

    private Boolean emotion;

    public static EmotionDto fromEmotionEntity(EmotionEntity emotionEntity) {
        return EmotionDto.builder()
            .id(emotionEntity.getId())
            .episodeId(emotionEntity.getEpisodeId())
            .commentId(emotionEntity.getCommentId())
            .emotion(emotionEntity.getEmotion())
            .build();
    }

    public static EmotionDto toQuery(SaveQuery saveQuery) {
        return EmotionDto.builder()
            .episodeId(saveQuery.getEpisodeId())
            .commentId(saveQuery.getCommentId())
            .emotion(saveQuery.getEmotion())
            .build();
    }
}
