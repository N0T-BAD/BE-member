package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.OAuthUseCase;
import com.blockpage.memberservice.application.port.in.OAuthUseCase.LoginQuery;
import com.blockpage.memberservice.application.port.out.OAuthDto;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/oauth")
@Slf4j
public class OAuthController {

    private final OAuthUseCase oAuthUseCase;

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<MemberView>> login(@RequestParam("code") String code, HttpServletResponse response) {
        OAuthDto oAuthDto = oAuthUseCase.oAuthLoginQuery(new LoginQuery(code));
        response.addHeader("accessToken", oAuthDto.getAccessToken());
        response.addHeader("accessTokenExpireTime", oAuthDto.getAccessTokenExpireTime().toString());
        response.addHeader("refreshToken", oAuthDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<MemberView>(new MemberView(oAuthDto.getMessage())));
    }
}
