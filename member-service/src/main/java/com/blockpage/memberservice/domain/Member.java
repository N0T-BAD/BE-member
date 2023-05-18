package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.UpdateQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private Long kakaoId;

    private String email;

    private String nickname;

    private String profileImage;

    private MultipartFile newProfileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    public Member(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public static Member findMember(FindMemberQuery findMemberQuery) {
        return Member.builder()
            .id(findMemberQuery.getId())
            .creatorNickname(findMemberQuery.getCreatorNickname())
            .build();
    }

    public static Member saveMember(KakaoUserInfoResponse kakaoUserInfoResponse) {
        return Member.builder()
            .kakaoId(Long.parseLong(kakaoUserInfoResponse.getId()))
            .email(kakaoUserInfoResponse.getKakao_account().getEmail())
            .nickname(kakaoUserInfoResponse.getProperties().getNickname())
            .profileImage(kakaoUserInfoResponse.getProperties().getProfile_image_url())
            .gender(kakaoUserInfoResponse.getKakao_account().getGender())
            .role(Role.MEMBER)
            .build();
    }

    public static Member fromMemberEntity(MemberEntity memberEntity) {
        return Member.builder()
            .kakaoId(memberEntity.getKakaoId())
            .email(memberEntity.getEmail())
            .nickname(memberEntity.getNickname())
            .profileImage(memberEntity.getProfileImage())
            .profileSkin(memberEntity.getProfileSkin())
            .gender(memberEntity.getGender())
            .role(memberEntity.getRole())
            .creatorNickname(memberEntity.getCreatorNickname())
            .adult(memberEntity.getAdult())
            .build();
    }

    public static Member fromUpdateQuery(@RequestBody UpdateQuery updateQuery) {
        return Member.builder()
            .id(updateQuery.getId())
            .nickname(updateQuery.getNickname())
            .newProfileImage(updateQuery.getProfileImage())
            .adult(updateQuery.getAdult())
            .build();
    }

    public static Member updateCreator(UpdateQuery updateQuery) {
        return Member.builder()
            .id(updateQuery.getId())
            .role(Role.AUTHOR)
            .creatorNickname(updateQuery.getCreatorNickname())
            .build();
    }
}
