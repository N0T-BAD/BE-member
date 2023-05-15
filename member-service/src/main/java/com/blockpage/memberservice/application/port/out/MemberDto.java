package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MemberDto {

    private Long kakaoId;

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    public static MemberDto fromMemberEntity(MemberEntity memberEntity){
        return MemberDto.builder()
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

}
