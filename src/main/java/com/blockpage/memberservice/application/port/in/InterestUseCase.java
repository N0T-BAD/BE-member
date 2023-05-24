package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.application.port.out.InterestDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public interface InterestUseCase {

    InterestDto postInterestQuery(PostQuery postQuery);

    List<InterestDto> findInterestQuery(FindQuery query);

    void deleteInterestQuery(DeleteQuery query);

    @Getter
    @Builder
    class PostQuery {

        private String memberEmail;
        private Long webtoonId;
        private String webtoonTitle;
        private String webtoonThumbnail;
        private String creator;
        private String illustrator;
        private String genre;

        public static PostQuery toQuery(String memberEmail, RequestInterest requestInterest) {
            return PostQuery.builder()
                .memberEmail(memberEmail)
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
    class FindQuery {

        private String memberEmail;

        public FindQuery(String memberEmail) {
            this.memberEmail = memberEmail;
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