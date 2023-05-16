package com.blockpage.memberservice.adaptor.external.kakao.RequestBody;

import com.blockpage.memberservice.adaptor.external.kakao.controller.OAuthConfig;
import com.blockpage.memberservice.application.port.in.OAuthUseCase.LoginQuery;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestToken {

    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;

    public static RequestToken tokenRequest(OAuthConfig oAuthConfig, LoginQuery loginQuery) {
        return RequestToken.builder()
            .grant_type(oAuthConfig.getType())
            .client_id(oAuthConfig.getClientId())
            .redirect_uri(oAuthConfig.getRedirectUrl())
            .code(loginQuery.getCode())
            .build();
    }
}
