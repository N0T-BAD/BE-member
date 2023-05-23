package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.entity.RatingEntity;
import com.blockpage.memberservice.application.port.in.RatingUseCase.SaveQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RatingDto {

    private Long episodeId;

    private Integer ratings;

    public static RatingDto toQuery(SaveQuery saveQuery) {
        return RatingDto.builder()
            .episodeId(saveQuery.getEpisodeId())
            .ratings(saveQuery.getRatings())
            .build();
    }

    public static RatingDto fromRatingEntity(RatingEntity ratingEntity) {
        return RatingDto.builder()
            .episodeId(ratingEntity.getEpisodeId())
            .ratings(ratingEntity.getRatings())
            .build();
    }

}