package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private Long kakaoId;

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    public Member(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public Member(FindMemberQuery findMemberQuery) {
        this.id = findMemberQuery.getId();
    }

    public static Member saveMember(KakaoUserInfoResponse kakaoUserInfoResponse) {
        return Member.builder()
            .kakaoId(Long.parseLong(kakaoUserInfoResponse.getId()))
            .email(kakaoUserInfoResponse.getKakao_account().getEmail())
            .nickname(kakaoUserInfoResponse.getProperties().getNickname())
            .profileImage(kakaoUserInfoResponse.getProperties().getProfile_image_url())
            .gender(kakaoUserInfoResponse.getKakao_account().getGender())
            .build();
    }

}
