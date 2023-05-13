package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.RatingUseCase.SaveQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Rating {

    private Long id;

    private MemberEntity memberEntity;

    private Long episodeId;

    private Integer ratings;

    public static Rating addRating(SaveQuery saveQuery, MemberEntity memberEntity) {
        return Rating.builder()
            .memberEntity(memberEntity)
            .episodeId(saveQuery.getEpisodeId())
            .ratings(saveQuery.getRatings())
            .build();
    }

}