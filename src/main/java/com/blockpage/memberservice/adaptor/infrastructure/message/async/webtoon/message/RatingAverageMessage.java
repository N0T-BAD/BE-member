package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingAverageMessage {

    private Long episodeId;
    private Integer totalScore;
    private Integer participantCount;

    public static RatingAverageMessage sendMessage(Long episodeId, Integer totalScore, Integer participantCount) {
        return RatingAverageMessage.builder()
            .episodeId(episodeId)
            .totalScore(totalScore)
            .participantCount(participantCount)
            .build();
    }

}