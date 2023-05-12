package com.blockpage.memberservice.adaptor.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "memberEntityId")
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
