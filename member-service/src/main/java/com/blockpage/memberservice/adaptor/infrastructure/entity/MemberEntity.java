package com.blockpage.memberservice.adaptor.infrastructure.entity;

import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long kakaoId;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String profileImage;

    @Column
    private String profileSkin;

    @Column
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String creatorNickname;

    @Column
    private Boolean adult;

}
