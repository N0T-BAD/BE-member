package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoUserInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.controller.OAuthConfig;
import com.blockpage.memberservice.application.port.in.OAuthUseCase;
import com.blockpage.memberservice.application.port.out.MemberDto;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.application.port.out.OAuthDto;
import com.blockpage.memberservice.application.port.out.OAuthPort;
import com.blockpage.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService implements OAuthUseCase {

    private final OAuthPort oAuthPort;
    private final MemberPort memberPort;
    @Autowired
    OAuthConfig oAuthConfig;


    @Override
    public OAuthDto oAuthLoginQuery(LoginQuery longinQuery) {

        log.info(oAuthConfig.getType());
        KakaoTokenResponse kakaoTokenResponse = oAuthPort.tokenRequest(oAuthConfig, longinQuery);
        KakaoTokenInfoResponse kakaoTokenInfoResponse = oAuthPort.tokenInfoRequest(kakaoTokenResponse);
        MemberDto memberDto = memberPort.findMember(new Member(Long.parseLong(kakaoTokenInfoResponse.getId())));
        KakaoResponse kakaoResponse = KakaoResponse.totalResponse(kakaoTokenResponse, kakaoTokenInfoResponse);
        if (memberDto != null) {
            String message = "로그인 되었습니다.";
            return OAuthDto.fromResponse(kakaoResponse, message);
        } else {
            KakaoUserInfoResponse kakaoUserInfoResponse = oAuthPort.userInfoRequest(kakaoResponse);
            memberPort.saveMember(Member.saveMember(kakaoUserInfoResponse));
            String message = "회원가입 되었습니다.";
            return OAuthDto.fromResponse(kakaoResponse, message);
        }
    }
}

