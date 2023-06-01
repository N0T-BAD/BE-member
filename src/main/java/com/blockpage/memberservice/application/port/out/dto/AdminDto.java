package com.blockpage.memberservice.application.port.out.dto;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
import com.blockpage.memberservice.domain.Admin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminDto {

    private String adminId;

    private Role role;

    public static AdminDto fromAdmin(Admin admin){
        return AdminDto.builder()
            .adminId(admin.getAdminId())
            .role(admin.getRole())
            .build();
    }
}
