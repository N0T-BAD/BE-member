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

    public RatingAverageMessage sum(RatingAverageMessage message){
        if(this.episodeId.equals(message.episodeId)){
            return new RatingAverageMessage(episodeId,this.totalScore+message.totalScore,
                this.participantCount+message.participantCount);
        }else{
            throw new IllegalArgumentException("서버에러");
        }
    }
}