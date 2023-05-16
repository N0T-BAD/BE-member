package com.blockpage.memberservice.adaptor.external.kakao.RequestBody;

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
