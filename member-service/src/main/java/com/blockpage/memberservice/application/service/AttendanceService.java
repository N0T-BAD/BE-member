package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.external.block.requestbody.RequestBlock;
import com.blockpage.memberservice.application.port.in.AttendanceUseCase;
import com.blockpage.memberservice.application.port.out.AttendancePort;
import com.blockpage.memberservice.application.port.out.BlockPort;
import com.blockpage.memberservice.domain.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService implements AttendanceUseCase {

    private final AttendancePort attendancePort;
    private final BlockPort blockPort;

    @Override
    public void postAttendance(PostQuery postQuery) {
        attendancePort.postAttendance(new Attendance(postQuery.getMemberEmail()));
        blockPort.postBlock(new RequestBlock(postQuery.getMemberEmail()));
    }
}