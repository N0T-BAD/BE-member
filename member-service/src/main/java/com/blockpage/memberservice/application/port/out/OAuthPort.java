package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.controller.OAuthConfig;
import com.blockpage.memberservice.application.port.in.OAuthUseCase.LoginQuery;

public interface OAuthPort {

    KakaoTokenResponse tokenRequest(OAuthConfig oAuthConfig, LoginQuery loginQuery);

    KakaoTokenInfoResponse tokenInfoRequest(KakaoTokenResponse kakaoTokenResponse);

    KakaoUserInfoResponse userInfoRequest(KakaoResponse kakaoResponse);
}
