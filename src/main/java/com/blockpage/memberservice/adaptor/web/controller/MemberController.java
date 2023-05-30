package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestMember;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.SignInQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.UpdateQuery;
import com.blockpage.memberservice.application.port.out.dto.MemberDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/v1/members")
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

    @RequestMapping(method = RequestMethod.PUT,headers = ("content-type=multipart/*"))
    public ResponseEntity<ApiResponse<MemberView>> updateMember(@RequestHeader("memberId") String email,
        @RequestParam("type") String type,
        @RequestPart RequestMember requestMember,
        @RequestPart(required = false) MultipartFile profileImage) throws IOException {
        memberUseCase.updateMemberInfo(UpdateQuery.toQuery(email, type, requestMember, profileImage));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView("회원정보가 변경되었습니다.")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MemberView>> findMember(@RequestHeader(value = "memberId",required = false) String email,
        @RequestParam("type") String type,
        @RequestParam(value = "creatorNickname",required = false) String nickName)  {
        MemberDto memberDto = memberUseCase.findMemberInfo(FindMemberQuery.toQuery(email, type, nickName));
        if (memberDto.getRole() != null) {
            MemberView memberView = new MemberView(memberDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(memberView));
        } else {
            MemberView memberView = new MemberView("사용가능한 닉네임입니다.");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(memberView));
        }
    }
}
