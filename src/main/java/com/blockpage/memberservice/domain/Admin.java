package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.AdminEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
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

    private Role role;

    public Admin(String adminId) {
        this.adminId = adminId;
    }

    public static Admin fromLogIn(LogInQuery logInQuery){
        return Admin.builder()
            .adminId(logInQuery.getAdminId())
            .password(logInQuery.getPassword())
            .build();
    }

    public static Admin fromEntity(AdminEntity adminEntity){
        return Admin.builder()
            .adminId(adminEntity.getAdminId())
            .role(adminEntity.getRole())
            .build();
    }
}