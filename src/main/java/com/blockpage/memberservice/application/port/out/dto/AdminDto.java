package com.blockpage.memberservice.application.port.out.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminDto {

    private String adminId;

    public AdminDto(String adminId) {
        this.adminId = adminId;
    }
}
