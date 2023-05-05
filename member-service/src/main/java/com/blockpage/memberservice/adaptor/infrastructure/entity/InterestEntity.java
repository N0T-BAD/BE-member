package com.blockpage.memberservice.adaptor.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

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

}
