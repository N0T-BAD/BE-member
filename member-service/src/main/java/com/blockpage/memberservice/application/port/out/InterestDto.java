package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.application.port.in.InterestUseCase.SaveQuery;
import com.blockpage.memberservice.domain.Interest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InterestDto {

    private Long id;

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

    public static InterestDto toQuery(SaveQuery saveQuery) {
        return InterestDto.builder()
            .webtoonId(saveQuery.getWebtoonId())
            .webtoonTitle(saveQuery.getWebtoonTitle())
            .webtoonThumbnail(saveQuery.getWebtoonThumbnail())
            .creator(saveQuery.getCreator())
            .genre(saveQuery.getGenre())
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
