package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.application.port.out.OAuthDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface OAuthUseCase {

    OAuthDto oAuthLoginQuery(LoginQuery longinQuery);

    @Getter
    @NoArgsConstructor
    class LoginQuery {

        String code;

        public LoginQuery(String code) {
            this.code = code;
        }
    }
}