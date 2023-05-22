package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.SignInQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.UpdateQuery;
import com.blockpage.memberservice.application.port.in.RequestMember;
import com.blockpage.memberservice.application.port.out.MemberDto;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberUseCase memberUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> signInMember(@RequestBody RequestMember requestMember) {
        MemberDto memberDto = memberUseCase.signInMember(SignInQuery.toQuery(requestMember));
        if (memberDto.getSignUp()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new MemberView(memberDto)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView(memberDto)));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MemberView>> updateMember(HttpSession session,
        @RequestParam("type") String type,
        @RequestPart RequestMember requestMember,
        @RequestPart(required = false) MultipartFile profileImage) throws IOException {
        memberUseCase.updateMemberInfo(UpdateQuery.toQuery(type, requestMember, profileImage, session));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView("회원정보가 변경되었습니다.")));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<MemberView>> findMember(HttpSession session,
        @RequestParam("type") String type,
        @RequestBody(required = false) RequestMember requestMember) {
        MemberDto memberDto = memberUseCase.findMemberInfo(FindMemberQuery.toQuery(type, requestMember, session));
        if (memberDto.getRole() != null) {
            MemberView memberView = new MemberView(memberDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(memberView));
        } else {
            MemberView memberView = new MemberView("사용가능한 닉네임입니다.");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(memberView));
        }
    }
}