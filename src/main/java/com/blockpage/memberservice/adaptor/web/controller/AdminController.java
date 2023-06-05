package com.blockpage.memberservice.adaptor.web.controller;

import static com.blockpage.memberservice.exception.ErrorCode.SESSION_EXPIRE;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestAdmin;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.AdminUseCase;
import com.blockpage.memberservice.application.port.in.AdminUseCase.LogInQuery;
import com.blockpage.memberservice.application.port.in.AdminUseCase.LogOutQuery;
import com.blockpage.memberservice.application.port.out.dto.AdminDto;
import com.blockpage.memberservice.exception.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/v1/admins")
public class AdminController {

    private final AdminUseCase adminUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> logInMember(HttpServletRequest httpServletRequest,
        @RequestBody RequestAdmin requestAdmin) {
        HttpSession session = httpServletRequest.getSession();
        AdminDto adminDto = adminUseCase.adminLogin(LogInQuery.toQuery(requestAdmin));
        session.setAttribute("memberId", adminDto.getAdminId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new MemberView(adminDto)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<MemberView>> logOutMember(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("memberId").toString() != null) {
            adminUseCase.adminLogOut(new LogOutQuery(session));
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView("로그아웃 되었습니다.")));
        } else {
            throw new CustomException(SESSION_EXPIRE.getMessage(), SESSION_EXPIRE.getHttpStatus());
        }
    }
}
