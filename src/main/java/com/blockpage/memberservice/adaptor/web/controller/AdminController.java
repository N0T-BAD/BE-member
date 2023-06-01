package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestAdmin;
import com.blockpage.memberservice.adaptor.web.view.AdminView;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.application.port.in.AdminUseCase;
import com.blockpage.memberservice.application.port.in.AdminUseCase.InfoQuery;
import com.blockpage.memberservice.application.port.in.AdminUseCase.LogInQuery;
import com.blockpage.memberservice.application.port.out.dto.AdminDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/v1/admin")
public class AdminController {

    private final AdminUseCase adminUseCase;
    private final RedisSessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<AdminView>> signInMember(HttpServletRequest httpServletRequest,
        @RequestBody RequestAdmin requestAdmin) {
        HttpSession session = httpServletRequest.getSession();
        AdminDto adminDto = adminUseCase.adminLogin(LogInQuery.toQuery(requestAdmin));
        session.setAttribute("id", adminDto.getAdminId());
        session.setAttribute("role", adminDto.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new AdminView("로그인되었습니다.")));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<AdminView>> signOutMember(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (sessionRepository.findById(session.getId()) != null) {
            adminUseCase.sessionInfo(new InfoQuery(session));
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new AdminView("로그아웃되었습니다.")));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(new AdminView("이미 만료된 세션입니다.")));
        }
    }
}
