package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.web.view.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.in.InterestUseCase.SaveQuery;
import com.blockpage.memberservice.application.port.in.RequestInterest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/interests")
@Slf4j
public class InterestController {

    private final InterestUseCase interestUseCase;

    @PostMapping
    public ResponseEntity<APIResponse> addInterst(@RequestBody RequestInterest requestInterest,
        @RequestHeader("accessToken") String token) {
        //TODO 헤더 토큰으로 멤버 가져오기 구현예정(토큰>카카오토큰정보조회>카카오아이디받아오기>멤버가져오기)
        MemberEntity memberEntity = MemberEntity.builder()
            .id(1L)
            .build();
        SaveQuery saveQuery = SaveQuery.toQuery(requestInterest);
        interestUseCase.saveInterestQuery(saveQuery, memberEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<MemberView>("찜생성 되었습니다."));
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
