package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.application.port.in.AdminUseCase.LogInQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Admin {

    private String adminId;

    private String password;

    public Admin(String adminId) {
        this.adminId = adminId;
    }

    public static Admin fromLogIn(LogInQuery logInQuery) {
        return Admin.builder()
            .adminId(logInQuery.getAdminId())
            .password(logInQuery.getPassword())
            .build();
    }
}