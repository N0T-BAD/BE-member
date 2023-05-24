package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.application.port.out.RatingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface RatingUseCase {

    RatingDto postRatingQuery(PostQuery postQuery);

    RatingDto findRatingQuery(FindQuery findQuery);

    @Getter
    @Builder
    class PostQuery {

        private String memberEmail;
        private Long episodeId;
        private Integer ratings;

        public static PostQuery toQuery(String memberEmail, RequestRating requestRating) {
            return PostQuery.builder()
                .memberEmail(memberEmail)
                .episodeId(requestRating.getEpisodeId())
                .ratings(requestRating.getRatings())
                .build();
        }
    }

    @Getter
    @Setter
    class FindQuery {

        private String memberEmail;
        private Long episodeId;

        public FindQuery(String memberEmail, Long episodeId) {
            this.memberEmail = memberEmail;
            this.episodeId = episodeId;
        }
    }

}