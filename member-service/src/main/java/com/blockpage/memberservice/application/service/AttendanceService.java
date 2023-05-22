package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.AttendanceUseCase;
import com.blockpage.memberservice.application.port.out.AttendancePort;
import com.blockpage.memberservice.domain.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService implements AttendanceUseCase {

    private final AttendancePort attendancePort;

    @Override
    public void postAttendance(PostQuery postQuery) {
        attendancePort.postAttendance(new Attendance(postQuery.getMemberEmail()));
    }
}