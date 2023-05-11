package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import lombok.Getter;

@Getter
public class Member {

    private Long kakaoId;

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;
}
