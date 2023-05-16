package com.blockpage.memberservice.adaptor.external.kakao.responseBody;

import lombok.Getter;

@Getter
public class KakaoUserInfoResponse {

    private String id;

    private KakaoAccount kakao_account;

    private KakaoProperties properties;

}
