package com.blockpage.memberservice.adaptor.external.kakao.controller;

import com.blockpage.memberservice.adaptor.external.kakao.requestBody.RequestToken;
import com.blockpage.memberservice.adaptor.external.kakao.responseBody.KakaoTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "kakao", url = "${kakao.root-uri}")
public interface KakaoOpenFeignController {

    //코드로 토큰 생성 요청
    @PostMapping(value = "${kakao.token-uri}")
    @Headers("Content-type: application/x-www-form-urlencoded;charset=utf-8")
    KakaoTokenResponse codeToToken(@SpringQueryMap RequestToken requestToken);


}
