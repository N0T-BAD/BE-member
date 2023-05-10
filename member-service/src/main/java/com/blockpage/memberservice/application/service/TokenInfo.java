package com.blockpage.memberservice.application.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String grantType;

    private String accessToken;

    private String refreshToken;

    private Long refreshTokenExpirationTime;

}
