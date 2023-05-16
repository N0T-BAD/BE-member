package com.blockpage.memberservice.adaptor.external.kakao.controller;

import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoTokenInfoResponse;
import com.blockpage.memberservice.adaptor.external.kakao.ResponseBody.KakaoUserInfoResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoInfo", url = "https://kapi.kakao.com")
public interface KakaoInfoOpenFeignCtroller {

    @GetMapping(value = "${kakao.token-info-uri}")
    KakaoTokenInfoResponse tokenInfo(@RequestHeader("Authorization") String token);

    @PostMapping(value = "${kakao.user-info-uri}")
    @Headers("Content-type: application/x-www-form-urlencoded;charset=utf-8")
    KakaoUserInfoResponse userInfo(@RequestHeader("Authorization") String token);
}