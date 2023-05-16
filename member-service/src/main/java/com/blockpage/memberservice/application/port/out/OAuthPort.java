package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoTokenResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.configuration.OAuthConfig;
import com.blockpage.memberservice.application.port.in.OAuthUseCase.LoginQuery;

public interface OAuthPort {

    KakaoTokenResponse tokenRequest(OAuthConfig oAuthConfig, LoginQuery loginQuery);

    KakaoTokenInfoResponse tokenInfoRequest(KakaoTokenResponse kakaoTokenResponse);

    KakaoUserInfoResponse userInfoRequest(KakaoResponse kakaoResponse);
}
