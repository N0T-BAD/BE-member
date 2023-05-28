package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.application.port.in.InterestUseCase.PostQuery;
import com.blockpage.memberservice.domain.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InterestDto {

    private Long id;

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

    private Boolean choice;

    public InterestDto(Boolean choice) {
        this.choice = choice;
    }

    public static InterestDto toQuery(PostQuery postQuery) {
        return InterestDto.builder()
            .webtoonId(postQuery.getWebtoonId())
            .webtoonTitle(postQuery.getWebtoonTitle())
            .webtoonThumbnail(postQuery.getWebtoonThumbnail())
            .creator(postQuery.getCreator())
            .genre(postQuery.getGenre())
            .build();
    }

    public static InterestDto fromInterest(Interest interest) {
        return InterestDto.builder()
            .webtoonId(interest.getWebtoonId())
            .webtoonTitle(interest.getWebtoonTitle())
            .webtoonThumbnail(interest.getWebtoonThumbnail())
            .creator(interest.getCreator())
            .illustrator(interest.getIllustrator())
            .genre(interest.getGenre())
            .build();
    }
}
