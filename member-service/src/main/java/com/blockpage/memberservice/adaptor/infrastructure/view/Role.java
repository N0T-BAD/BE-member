package com.blockpage.memberservice.adaptor.infrastructure.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    MEMBER("회원"),
    AUTHOR("작가"),
    ADMIN("관리자"),
    ;

    private String value;

    public static Role findRoleTypeByKey(String value){
        return Arrays.stream(Role.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .get();
    }
}
