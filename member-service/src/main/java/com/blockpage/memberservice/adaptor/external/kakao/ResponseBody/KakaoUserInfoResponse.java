package com.blockpage.memberservice.adaptor.external.kakao.ResponseBody;

import lombok.Getter;

@Getter
public class KakaoUserInfoResponse {

    private String id;

    private KakaoAccount kakao_account;

    private KakaoProperties properties;

}
