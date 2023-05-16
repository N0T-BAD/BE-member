package com.blockpage.memberservice.adaptor.external.kakao.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestInfo {

    private String accessToken;

    public RequestInfo(String accessToken) {
        this.accessToken = "Bearer " + accessToken;
    }
}
