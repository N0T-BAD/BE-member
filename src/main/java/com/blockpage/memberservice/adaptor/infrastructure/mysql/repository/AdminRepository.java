package com.blockpage.memberservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.AdminEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByAdminId(String adminId);
}