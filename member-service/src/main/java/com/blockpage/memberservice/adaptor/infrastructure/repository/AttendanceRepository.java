package com.blockpage.memberservice.adaptor.infrastructure.repository;

import com.blockpage.memberservice.adaptor.infrastructure.entity.AttendanceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    Optional<AttendanceEntity> findByMemberEmailAndRegisterTimeAfter(String email, LocalDateTime today);
}
