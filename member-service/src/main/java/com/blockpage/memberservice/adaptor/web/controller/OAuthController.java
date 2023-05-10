package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.application.service.JwtTokenProvider;
import com.blockpage.memberservice.application.service.OAuthService;
import com.blockpage.memberservice.application.service.TokenInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/oauth")
@Slf4j
public class OAuthController {

    private final OAuthService oAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;

    //로그인 테스트 주소
    @ResponseBody
    @GetMapping("/login")
    public ResponseEntity<APIResponse> Login(@RequestParam("code") String code, HttpServletResponse response)
        throws JsonProcessingException {
        System.out.println(code);
        //코드 통해 카카오 회원 검증하여 email 가져오기
        String email = oAuthService.kakaoLogin(code);
        TokenInfo token = jwtTokenProvider.generateToken(email);
        redisTemplate.opsForValue().set(email, token.getRefreshToken(), token.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        response.addHeader("accessToken", token.getAccessToken());
        response.addHeader("refreshToken", token.getRefreshToken());
        log.info(token.toString());
        return ResponseEntity.ok().body(new APIResponse("로그인 되었습니다.", token));
    }
}
