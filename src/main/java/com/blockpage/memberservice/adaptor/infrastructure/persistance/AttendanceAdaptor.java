package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.AttendanceEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.AttendanceRepository;
import com.blockpage.memberservice.application.port.out.AttendancePort;
import com.blockpage.memberservice.domain.Attendance;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttendanceAdaptor implements AttendancePort {

    private final AttendanceRepository attendanceRepository;

    @Override
    public void postAttendance(Attendance attendance) {
        Optional<AttendanceEntity> attendanceEntity = attendanceRepository.findByMemberEmailAndRegisterTimeAfter(
            attendance.getMemberEmail(),
            LocalDate.now().minusDays(1).atTime(11, 59, 59));
        log.info(attendanceEntity.toString());
        if (attendanceEntity.isEmpty()) {
            attendanceRepository.save(AttendanceEntity.postAttendance(attendance));
        } else {
            throw new RuntimeException("이미 출석체크 하셨습니다.");
        }
    }
}
