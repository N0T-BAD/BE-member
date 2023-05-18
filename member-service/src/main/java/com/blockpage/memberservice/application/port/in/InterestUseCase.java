package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.out.InterestDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public interface InterestUseCase {

    InterestDto saveInterestQuery(SaveQuery query, MemberEntity memberEntity);

    List<InterestDto> findInterestQuery(FindQuery query);

    void deleteInterestQuery(DeleteQuery query);

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

    @Getter
    @Builder
    class FindQuery {

        private Long memberId;

        public static FindQuery toQuery(Long id) {

            return FindQuery.builder()
                .memberId(id)
                .build();
        }
    }

    @Getter
    @Builder
    class DeleteQuery {

        private Long id;

        public static DeleteQuery toQuery(Long id) {
            return DeleteQuery.builder()
                .id(id)
                .build();
        }
    }

}