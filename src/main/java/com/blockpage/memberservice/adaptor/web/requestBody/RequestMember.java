package com.blockpage.memberservice.adaptor.web.requestBody;

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
