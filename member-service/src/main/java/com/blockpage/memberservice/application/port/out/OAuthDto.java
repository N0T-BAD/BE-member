package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthDto {

    private String message;

    private String accessToken;

    private Integer accessTokenExpireTime;

    private String refreshToken;

    private Integer refreshTokenExpireTime;

    public static OAuthDto fromResponse(KakaoResponse kakaoResponse, String message) {
        return OAuthDto.builder()
            .message(message)
            .accessToken(kakaoResponse.getAccessToken())
            .accessTokenExpireTime(Integer.parseInt(kakaoResponse.getExpiresIn()))
            .refreshToken(kakaoResponse.getRefreshToken())
            .accessTokenExpireTime(Integer.parseInt(kakaoResponse.getRefreshTokenExpiresIn()))
            .build();
    }

}
