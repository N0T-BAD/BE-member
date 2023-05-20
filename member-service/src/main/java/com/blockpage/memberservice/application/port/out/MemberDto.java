package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.blockpage.memberservice.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class MemberDto {

    private String uuid;

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    public MemberDto(String uuid) {
        this.uuid = uuid;
    }

    public static MemberDto joinMember(Member member) {
        return MemberDto.builder()
            .uuid(member.getUuid())
            .email(member.getEmail())
            .build();
    }

    public static MemberDto fromMember(Member member) {
        return MemberDto.builder()
            .uuid(member.getUuid())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImage(member.getProfileImage())
            .profileSkin(member.getProfileSkin())
            .gender(member.getGender())
            .role(member.getRole())
            .creatorNickname(member.getCreatorNickname())
            .adult(member.getAdult())
            .build();
    }

}
