package com.blockpage.memberservice.application.port.out.dto;

import com.blockpage.memberservice.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmotionDto {

    private Long id;

    private Long episodeId;

    private Long commentId;

    private Boolean choice;

    private Boolean emotion;



    public EmotionDto(Boolean emotion) {
        this.emotion = emotion;
    }

    public EmotionDto(Boolean choice, Boolean emotion) {
        this.choice = choice;
        this.emotion = emotion;
    }

    public static EmotionDto fromEmotion(Emotion emotion) {
        return EmotionDto.builder()
            .id(emotion.getId())
            .commentId(emotion.getCommentId())
            .emotion(emotion.getEmotion())
            .build();
    }
}
