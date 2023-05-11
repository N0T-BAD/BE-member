package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.entity.RatingEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RequestRating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ratings")
@Slf4j
public class RatingsController {

    @PostMapping
    public ResponseEntity<APIResponse> addRating(@RequestBody RequestRating requestRating) {
        RatingEntity ratingEntity = RatingEntity.builder()
            .memberEntity(
                MemberEntity.builder().
                    email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("프로필이미지")
                    .role(Role.MEMBER)
                    .adult(false)
                    .build())
            .episodeId(1L)
            .ratings(8)
            .build();
        return ResponseEntity.ok().body(new APIResponse("평점 등록되었습니다.", requestRating.getRatings()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getInterest(@RequestParam("episodeId") Long episodeId) {
        return ResponseEntity.ok().body(new APIResponse(new MemberView(8)));
    }

}
