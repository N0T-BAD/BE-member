package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.AdminUseCase;
import com.blockpage.memberservice.application.port.out.dto.AdminDto;
import com.blockpage.memberservice.application.port.out.port.AdminPort;
import com.blockpage.memberservice.domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminUseCase {

    private final AdminPort adminPort;

    @Override
    public AdminDto adminLogin(LogInQuery logInQuery) {
        Admin admin = adminPort.findId(Admin.fromLogIn(logInQuery));
        return AdminDto.fromAdmin(admin);
    }

    @Override
    public void adminLogOut(LogOutQuery logOutQuery) {
        adminPort.findId(new Admin(logOutQuery.getAdminId()));
    }
}
