package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/interests")
@Slf4j
public class InterestController {

    @GetMapping
    public ResponseEntity<APIResponse> getInterests() {
        List<MemberView> memberViewList = new ArrayList<>();
        memberViewList.add(new MemberView(1L, 1L, "웹툰제목", "웹툰썸네일", "작가명", "만화가명", "코믹"));
        memberViewList.add(new MemberView(2L, 1L, "NOT-BAD", "프로핅스킨", "고은", "고은", "코믹"));
        memberViewList.add(new MemberView(3L, 1L, "NOT-BAD", "프로핅스킨", "고은", "고은", "코믹"));
        return ResponseEntity.ok().body(new APIResponse(memberViewList));
    }
}
