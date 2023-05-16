package com.blockpage.memberservice.adaptor.infrastructure.entity;

import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.domain.Member;
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
public class MemberEntity extends BaseEntity {

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

    public static MemberEntity fromMember(Member member) {
        return MemberEntity.builder()
            .kakaoId(member.getKakaoId())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImage(member.getProfileImage() != null ? member.getProfileImage() : "프로필 이미지를 선택하지 않았습니다.")
            .gender(member.getGender() != null ? member.getGender() : "성별이 입력되지 않았습니다.")
            .build();
    }

    public static MemberEntity updateMember(MemberEntity memberEntity, Member member) {
        return MemberEntity.builder()
            .id(memberEntity.getId())
            .kakaoId(memberEntity.getKakaoId())
            .email(memberEntity.getEmail())
            .nickname(member.getNickname())
            .profileImage(member.getProfileImage() != null ? member.getProfileImage() : memberEntity.getProfileImage())
            .gender(member.getGender() != null ? member.getGender() : memberEntity.getGender())
            .role(memberEntity.getRole())
            .creatorNickname(memberEntity.getCreatorNickname())
            .adult(member != null ? member.getAdult() : memberEntity.getAdult())
            .build();
    }

}
