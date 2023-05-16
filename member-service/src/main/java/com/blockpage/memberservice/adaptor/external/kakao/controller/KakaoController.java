package com.blockpage.memberservice.adaptor.external.kakao.controller;

import com.blockpage.memberservice.adaptor.external.kakao.configuration.OAuthConfig;
import com.blockpage.memberservice.adaptor.external.kakao.requestBody.RequestInfo;
import com.blockpage.memberservice.adaptor.external.kakao.requestBody.RequestToken;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoTokenResponse;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.application.port.in.OAuthUseCase.LoginQuery;
import com.blockpage.memberservice.application.port.out.OAuthPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoController implements OAuthPort {

    private final KakaoOpenFeignController kakaoOpenFeignController;
    private final KakaoInfoOpenFeignController kakaoInfoOpenFeignController;

    @Override
    public KakaoTokenResponse tokenRequest(OAuthConfig oAuthConfig, LoginQuery loginQuery) {
        //토큰생성
        KakaoTokenResponse kakaoTokenResponse = kakaoOpenFeignController.codeToToken(RequestToken.tokenRequest(oAuthConfig, loginQuery));
        return kakaoTokenResponse;
    }

    @Override
    public KakaoTokenInfoResponse tokenInfoRequest(KakaoTokenResponse kakaoTokenResponse) {
        //토큰 유효성확인 및 카카오아이디 조회 통한 멤버 확인
        RequestInfo requestInfo = new RequestInfo(kakaoTokenResponse.getAccess_token());
        KakaoTokenInfoResponse kakaoTokenInfoResponse = kakaoInfoOpenFeignController.tokenInfo(requestInfo.getAccessToken());
        return kakaoTokenInfoResponse;
    }

    @Override
    public KakaoUserInfoResponse userInfoRequest(KakaoResponse kakaoResponse) {
        RequestInfo requestInfo = new RequestInfo(kakaoResponse.getAccessToken());
        log.info(requestInfo.getAccessToken());
        //토큰으로 유저 정보 가져오기
        KakaoUserInfoResponse kakaoUserInfoResponse = kakaoInfoOpenFeignController.userInfo(requestInfo.getAccessToken());
        log.info(kakaoUserInfoResponse.getKakao_account().getEmail());
        log.info(kakaoUserInfoResponse.getProperties().getNickname());
        return kakaoUserInfoResponse;
    }


}