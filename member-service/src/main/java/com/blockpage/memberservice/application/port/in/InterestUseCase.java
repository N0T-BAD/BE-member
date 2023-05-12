package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.out.InterestDto;
import lombok.Builder;
import lombok.Getter;

public interface InterestUseCase {

    InterestDto saveInterestQuery(SaveQuery query, MemberEntity memberEntity);

    @Getter
    @Builder
    class SaveQuery {

        private Long webtoonId;
        private String webtoonTitle;
        private String webtoonThumbnail;
        private String creator;
        private String illustrator;
        private String genre;

        public static SaveQuery toQuery(RequestInterest requestInterest) {
            return SaveQuery.builder()
                .webtoonId(requestInterest.getWebtoonId())
                .webtoonTitle(requestInterest.getWebtoonTitle())
                .webtoonThumbnail(requestInterest.getWebtoonThumbnail())
                .creator(requestInterest.getCreator())
                .illustrator(requestInterest.getIllustrator())
                .genre(requestInterest.getGenre())
                .build();
        }
    }

}
