package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RequestInterest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/interests")
@Slf4j
public class InterestController {

    @PostMapping
    public ResponseEntity<APIResponse> addInterest(@RequestBody RequestInterest requestInterest) {
        InterestEntity interestEntity = InterestEntity.builder()
            .memberEntity(MemberEntity.builder()
                .email("abc@naver.com")
                .nickname("고은")
                .profileImage("프로필이미지")
                .role(Role.MEMBER)
                .adult(false)
                .build())
            .webtoonId(requestInterest.getWebtoonId())
            .webtoonTitle(requestInterest.getWebtoonTitle())
            .webtoonThumbnail(requestInterest.getWebtoonThumbnail())
            .creator(requestInterest.getCreator())
            .genre(requestInterest.getGenre())
            .build();
        log.info(interestEntity.toString());
        return ResponseEntity.ok().body(new APIResponse("찜 등록되었습니다.", requestInterest.getWebtoonTitle()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getInterest() {
        List<MemberView> memberViewList = new ArrayList<>();
        memberViewList.add(new MemberView(1L, 1L, "웹툰제목", "웹툰썸네일", "작가명", "만화가명", "코믹"));
        memberViewList.add(new MemberView(2L, 1L, "NOT-BAD", "프로핅스킨", "고은", "고은", "코믹"));
        memberViewList.add(new MemberView(3L, 1L, "NOT-BAD", "프로핅스킨", "고은", "고은", "코믹"));
        return ResponseEntity.ok().body(new APIResponse(memberViewList));
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteInterest(@PathVariable Long id) {
        return ResponseEntity.ok().body(new APIResponse("찜 삭제 되었습니다."));
    }
}
