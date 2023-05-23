package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.InterestUseCase.SaveQuery;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Interest {

    private Long id;

    private MemberEntity memberEntity;

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

    public static Interest addInterest(SaveQuery saveQuery, MemberEntity memberEntity) {
        return Interest.builder()
            .memberEntity(memberEntity)
            .webtoonId(saveQuery.getWebtoonId())
            .webtoonTitle(saveQuery.getWebtoonTitle())
            .webtoonThumbnail(saveQuery.getWebtoonThumbnail())
            .creator(saveQuery.getCreator())
            .illustrator(saveQuery.getIllustrator())
            .genre(saveQuery.getGenre())
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
