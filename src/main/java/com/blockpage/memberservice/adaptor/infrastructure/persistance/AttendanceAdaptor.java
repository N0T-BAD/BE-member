package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.adaptor.infrastructure.entity.AttendanceEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.AttendanceRepository;
import com.blockpage.memberservice.application.port.out.port.AttendancePort;
import com.blockpage.memberservice.domain.Attendance;
import com.blockpage.memberservice.exception.CustomException;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AttendanceAdaptor implements AttendancePort {

    private final AttendanceRepository attendanceRepository;

    @Override
    @Transactional
    public void postAttendance(Attendance attendance) {
        Optional<AttendanceEntity> attendanceEntity = attendanceRepository.findByMemberEmailAndRegisterTimeAfter(
            attendance.getMemberEmail(),
            LocalDate.now().minusDays(1).atTime(11, 59, 59));
        if (attendanceEntity.isEmpty()) {
            attendanceRepository.save(AttendanceEntity.postAttendance(attendance));
        } else {
            throw new CustomException(ATTENDANCE_ALREADY_POST.getMessage(), ATTENDANCE_ALREADY_POST.getHttpStatus());
        }
    }
}
