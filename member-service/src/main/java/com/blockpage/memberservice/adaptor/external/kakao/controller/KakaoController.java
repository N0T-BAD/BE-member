package com.blockpage.memberservice.adaptor.external.kakao.controller;

import com.blockpage.memberservice.adaptor.external.kakao.RequestBody.RequestInfo;
import com.blockpage.memberservice.adaptor.external.kakao.RequestBody.RequestToken;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoUserInfoResponse;
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
    private final KakaoInfoOpenFeignCtroller kakaoInfoOpenFeignCtroller;

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
        KakaoTokenInfoResponse kakaoTokenInfoResponse = kakaoInfoOpenFeignCtroller.tokenInfo(requestInfo.getAccessToken());
        return kakaoTokenInfoResponse;
    }

    @Override
    public KakaoUserInfoResponse userInfoRequest(KakaoResponse kakaoResponse) {
        RequestInfo requestInfo = new RequestInfo(kakaoResponse.getAccessToken());
        log.info(requestInfo.getAccessToken());
        //토큰으로 유저 정보 가져오기
        KakaoUserInfoResponse kakaoUserInfoResponse = kakaoInfoOpenFeignCtroller.userInfo(requestInfo.getAccessToken());
        log.info(kakaoUserInfoResponse.getKakao_account().getEmail());
        log.info(kakaoUserInfoResponse.getProperties().getNickname());
        return kakaoUserInfoResponse;
    }


}