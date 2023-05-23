package com.blockpage.memberservice.adaptor.infrastructure.entity;

import com.blockpage.memberservice.domain.Emotion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emotion")
public class EmotionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private Long episodeId;

    @Column
    private Long commentId;

    @Column
    private Boolean emotion;
    //true = 좋아요/false=싫어요

    public static EmotionEntity fromEmotion(Emotion emotion) {
        return EmotionEntity.builder()
            .memberEmail(emotion.getMemberEmail())
            .episodeId(emotion.getEpisodeId())
            .commentId(emotion.getCommentId())
            .emotion(emotion.getEmotion())
            .build();
    }
}
