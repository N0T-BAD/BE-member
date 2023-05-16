package com.blockpage.memberservice.adaptor.external.kakao.responseBody;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoResponse {

    private String token_type;

    private String accessToken;

    private String expiresIn;

    private String refreshToken;

    private String refreshTokenExpiresIn;

    private String kakaoId;

    public static KakaoResponse totalResponse(KakaoTokenResponse kakaoTokenResponse, KakaoTokenInfoResponse kakaoTokenInfoResponse) {
        return KakaoResponse.builder()
            .token_type(kakaoTokenResponse.getToken_type())
            .accessToken(kakaoTokenResponse.getAccess_token())
            .expiresIn(kakaoTokenResponse.getExpires_in())
            .refreshToken(kakaoTokenResponse.getRefresh_token())
            .refreshTokenExpiresIn(kakaoTokenResponse.getRefresh_token_expires_in())
            .kakaoId(kakaoTokenInfoResponse.getId())
            .build();
    }

}
