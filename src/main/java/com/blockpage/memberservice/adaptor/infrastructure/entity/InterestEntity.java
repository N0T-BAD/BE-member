package com.blockpage.memberservice.adaptor.infrastructure.entity;

import com.blockpage.memberservice.domain.Interest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interest")
public class InterestEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private Long webtoonId;

    @Column
    private String webtoonTitle;

    @Column
    private String webtoonThumbnail;

    @Column
    private String creator;

    @Column
    private String illustrator;

    @Column
    private String genre;

    public static InterestEntity fromInterest(Interest interest) {
        return InterestEntity.builder()
            .memberEmail(interest.getMemberEmail())
            .webtoonId(interest.getWebtoonId())
            .webtoonTitle(interest.getWebtoonTitle())
            .creator(interest.getCreator())
            .illustrator(interest.getIllustrator())
            .webtoonThumbnail(interest.getWebtoonThumbnail())
            .genre(interest.getGenre())
            .build();
    }

}
