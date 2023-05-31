package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.RatingEntity;
import com.blockpage.memberservice.application.port.in.RatingUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.RatingUseCase.PostQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Rating {

    private Long id;

    private String memberEmail;

    private Long webtoonId;

    private Long episodeId;

    private Integer ratings;

    public Rating(Integer ratings) {
        this.ratings = ratings;
    }


    public static Rating postRating(PostQuery postQuery) {
        return Rating.builder()
            .memberEmail(postQuery.getMemberEmail())
            .webtoonId(postQuery.getWebtoonId())
            .episodeId(postQuery.getEpisodeId())
            .ratings(postQuery.getRatings())
            .build();
    }

    public Rating(FindQuery findQuery) {
        this.memberEmail = findQuery.getMemberEmail();
        this.episodeId = findQuery.getEpisodeId();
    }

    public static Rating fromEntity(RatingEntity ratingEntity){
        return Rating.builder()
            .webtoonId(ratingEntity.getWebtoonId())
            .episodeId(ratingEntity.getEpisodeId())
            .ratings(ratingEntity.getRatings())
            .build();
    }

}