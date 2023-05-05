package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    @GetMapping
    public ResponseEntity<APIResponse> getMember() {
        MemberView memberView = new MemberView("고은",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", null,
            Role.MEMBER, null);
        return ResponseEntity.ok().body(new APIResponse(memberView));
    }
}
