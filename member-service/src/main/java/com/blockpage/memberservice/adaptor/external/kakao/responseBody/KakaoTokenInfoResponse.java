package com.blockpage.memberservice.adaptor.external.kakao.responseBody;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoTokenInfoResponse {

    private String expiresInMillis;
    private String id;
    private String expires_in;
    private String app_id;
    private String appId;

}
