package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import com.blockpage.memberservice.application.port.in.InterestUseCase.PostQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Interest {

    private Long id;

    private String memberEmail;

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

    public static Interest postInterest(PostQuery postQuery) {
        return Interest.builder()
            .memberEmail(postQuery.getMemberEmail())
            .webtoonId(postQuery.getWebtoonId())
            .webtoonTitle(postQuery.getWebtoonTitle())
            .webtoonThumbnail(postQuery.getWebtoonThumbnail())
            .creator(postQuery.getCreator())
            .illustrator(postQuery.getIllustrator())
            .genre(postQuery.getGenre())
            .build();
    }

    public static Interest findInterest(InterestEntity interestEntity) {
        return Interest.builder()
            .id(interestEntity.getId())
            .webtoonId(interestEntity.getWebtoonId())
            .webtoonTitle(interestEntity.getWebtoonTitle())
            .webtoonThumbnail(interestEntity.getWebtoonThumbnail())
            .creator(interestEntity.getCreator())
            .illustrator(interestEntity.getIllustrator())
            .genre(interestEntity.getGenre())
            .build();
    }

}
