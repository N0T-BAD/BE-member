package com.blockpage.memberservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestMember {

    private String email;

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private String gender;

    private String creatorNickname;

    private Boolean adult;
}
