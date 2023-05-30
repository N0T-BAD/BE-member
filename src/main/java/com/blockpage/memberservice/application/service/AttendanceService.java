package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.requestbody.RequestBlock;
import com.blockpage.memberservice.application.port.in.AttendanceUseCase;
import com.blockpage.memberservice.application.port.out.port.AttendancePort;
import com.blockpage.memberservice.application.port.out.port.BlockPort;
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
        blockPort.postBlock(postQuery.getMemberEmail(), new RequestBlock());
    }
}