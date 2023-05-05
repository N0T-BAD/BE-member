package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/emotions")
public class EmotionController {

    @GetMapping
    public ResponseEntity<APIResponse> getEmotion(@RequestParam("episodeId") Long episodeId) {
        List<MemberView> memberViewList = new ArrayList<>();

        memberViewList.add(new MemberView(1L, 1L, (Boolean) true));
        memberViewList.add(new MemberView(2L, 3L, (Boolean) false));
        memberViewList.add(new MemberView(3L, 5L, (Boolean) true));

        return ResponseEntity.ok().body(new APIResponse<>(memberViewList));
    }

}
