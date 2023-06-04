package com.blockpage.memberservice.application.port.out.dto;

import com.blockpage.memberservice.domain.Admin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminDto {

    private String adminId;

    private String name;

    public static AdminDto fromAdmin(Admin admin) {
        return AdminDto.builder()
            .adminId(admin.getAdminId())
            .name(admin.getName())
            .build();
    }
}
