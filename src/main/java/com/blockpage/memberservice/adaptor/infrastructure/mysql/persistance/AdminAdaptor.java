package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.ID_NOT_EXIST;
import static com.blockpage.memberservice.exception.ErrorCode.PASSWORD_DISCORD;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.AdminEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.AdminRepository;
import com.blockpage.memberservice.application.port.out.port.AdminPort;
import com.blockpage.memberservice.domain.Admin;
import com.blockpage.memberservice.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAdaptor implements AdminPort {

    private final AdminRepository adminRepository;

    @Override
    public Admin login(Admin admin) {
        Optional<AdminEntity> adminEntity = adminRepository.findByAdminId(admin.getAdminId());
        if (adminEntity.isEmpty()) {
            throw new CustomException(ID_NOT_EXIST.getMessage(), ID_NOT_EXIST.getHttpStatus());
        } else if (adminEntity.get().getPassword().equals(admin.getPassword())) {
            return Admin.fromEntity(adminEntity.get());
        } else {
            throw new CustomException(PASSWORD_DISCORD.getMessage(), PASSWORD_DISCORD.getHttpStatus());
        }
    }

    @Override
    public void info(Admin admin) {
        Optional<AdminEntity> adminEntity = adminRepository.findByAdminId(admin.getAdminId());
        if (adminEntity.isEmpty()) {
            throw new CustomException(ID_NOT_EXIST.getMessage(), ID_NOT_EXIST.getHttpStatus());
        }
    }

}