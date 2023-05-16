package com.blockpage.memberservice.adaptor.external.kakao.responseBody;

import lombok.Getter;

@Getter
public class KakaoTokenResponse {

    private String token_type;

    private String access_token;

    private String expires_in;

    private String refresh_token;

    private String refresh_token_expires_in;
}
