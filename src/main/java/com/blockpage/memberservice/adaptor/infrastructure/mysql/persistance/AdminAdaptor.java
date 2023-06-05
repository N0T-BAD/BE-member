package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.AdminEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.AdminRepository;
import com.blockpage.memberservice.application.port.out.port.AdminPort;
import com.blockpage.memberservice.domain.Admin;
import com.blockpage.memberservice.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminAdaptor implements AdminPort {

    private final AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public Admin findId(Admin admin) {
        AdminEntity adminEntity = adminRepository.findByAdminId(admin.getAdminId())
            .orElseThrow(() -> new CustomException(ID_NOT_EXIST.getMessage(), ID_NOT_EXIST.getHttpStatus()));
        return Admin.fromEntity(adminEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findPassword(Admin admin) {
        AdminEntity adminEntity = adminRepository.findByAdminId(admin.getAdminId()).get();
        if (adminEntity.getPassword().equals(admin.getPassword())) {
            return admin;
        } else {
            throw new CustomException(PASSWORD_DISCORD.getMessage(), PASSWORD_DISCORD.getHttpStatus());
        }
    }
}