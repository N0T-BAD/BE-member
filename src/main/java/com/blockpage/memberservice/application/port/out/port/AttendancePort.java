package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Attendance;

public interface AttendancePort {

    void postAttendance(Attendance attendance);
}
