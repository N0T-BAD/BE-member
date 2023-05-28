package com.blockpage.memberservice.application.port.out.dto;

import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.blockpage.memberservice.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class MemberDto {

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    private Boolean signUp;
    //true 일시 최초로그인(회원가입)

    public static MemberDto signIn(Member member) {
        return MemberDto.builder()
            .signUp(member.getSignUp())
            .role(member.getRole())
            .build();
    }

    public static MemberDto fromMember(Member member) {
        return MemberDto.builder()
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
