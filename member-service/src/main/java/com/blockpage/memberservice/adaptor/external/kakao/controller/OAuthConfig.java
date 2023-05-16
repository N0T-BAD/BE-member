package com.blockpage.memberservice.adaptor.external.kakao.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OAuthConfig {

    @Value("${kakao.authorization-grant-type}")
    private String type;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUrl;

    public OAuthConfig() {
    }
}


