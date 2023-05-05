package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RequestMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@Slf4j
public class MemberController {

    @PostMapping
    public ResponseEntity<APIResponse> joinMember(@RequestBody RequestMember requestMember) {
        MemberEntity memberEntity = MemberEntity.builder()
            .email(requestMember.getEmail())
            .nickname(requestMember.getNickname() != null ? requestMember.getNickname() : "닉네임을 설정해 보세요")
            .profileImage(requestMember.getProfileImage() != null ? requestMember.getProfileImage() : "기본스킨제공")
            .gender(requestMember.getGender() != null ? requestMember.getGender() : "성별 등록되지 않음")
            .role(Role.MEMBER)
            .adult(false)
            .build();
        log.info(memberEntity.toString());
        return ResponseEntity.ok().body(new APIResponse("회원가입이 완료되었습니다."));
    }



    @GetMapping
    public ResponseEntity<APIResponse> getMember() {
        MemberView memberView = new MemberView("고은",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", null,
            Role.MEMBER, null);
        return ResponseEntity.ok().body(new APIResponse(memberView));
    }
}
