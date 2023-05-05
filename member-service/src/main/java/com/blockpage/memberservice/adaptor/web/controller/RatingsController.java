package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ratings")
@Slf4j
public class RatingsController {

    @GetMapping
    public ResponseEntity<APIResponse> getInterests(@RequestParam("episode_id") Long episodeId) {
        MemberView memberView = MemberView.builder()
            .ratings(8)
            .build();
        return ResponseEntity.ok().body(new APIResponse(memberView));
    }


}
