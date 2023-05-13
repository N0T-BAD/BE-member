package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.out.RatingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface RatingUseCase {

    RatingDto saveRatingQuery(SaveQuery query, MemberEntity memberEntity);

    RatingDto findRatingQuery(FindQuery findQuery, Long memberId);

    @Getter
    @Builder
    class SaveQuery {

        private Long episodeId;
        private Integer ratings;

        public static SaveQuery toQuery(RequestRating requestRating) {
            return SaveQuery.builder()
                .episodeId(requestRating.getEpisodeId())
                .ratings(requestRating.getRatings())
                .build();
        }
    }

    @Getter
    @Setter
    class FindQuery {

        private Long episodeId;

        public FindQuery(Long episodeId) {
            this.episodeId = episodeId;
        }
    }

}