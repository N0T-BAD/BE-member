package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.AttendanceUseCase;
import com.blockpage.memberservice.application.port.in.AttendanceUseCase.PostQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/v1/attendance")
public class AttendanceController {

    private final AttendanceUseCase attendanceUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> postAttendance(@RequestHeader("memberId") String email) {
        attendanceUseCase.postAttendance(new PostQuery(email));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new MemberView("출석체크 되었습니다.")));
    }
}
