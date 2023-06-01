package com.blockpage.memberservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
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
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImage(member.getProfileImage() != null ? member.getProfileImage()
                : "https://storage.googleapis.com/blockpage-bucket/c1ad0198-d375-4297-a7ce-e1e67a48c0a6%20.png")
            .profileSkin(member.getProfileSkin() != null ? member.getProfileSkin()
                : "https://storage.googleapis.com/blockpage-bucket/4d6f32a5-c2f4-4d1e-adb6-4fe1048b6e08.png")
            .role(member.getRole())
            .gender(member.getGender())
            .adult(member.getAdult())
            .build();
    }

    public static MemberEntity updateMember(MemberEntity memberEntity, Member member) {
        return MemberEntity.builder()
            .id(memberEntity.getId())
            .email(memberEntity.getEmail())
            .nickname(member.getNickname() != null ? member.getNickname() : memberEntity.getNickname())
            .profileImage(member.getProfileImage() != null ? member.getProfileImage() : memberEntity.getProfileImage())
            .profileSkin(member.getProfileSkin() != null ? member.getProfileSkin() : memberEntity.getProfileSkin())
            .gender(member.getGender() != null ? member.getGender() : memberEntity.getGender())
            .role(member.getRole() != null ? member.getRole() : memberEntity.getRole())
            .creatorNickname(member.getCreatorNickname() != null ? member.getCreatorNickname() : memberEntity.getCreatorNickname())
            .adult(member.getAdult() != null ? member.getAdult() : memberEntity.getAdult())
            .build();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }

    public void setProfileSkin(String profileSkin) {
        this.profileSkin = profileSkin;
    }
}
