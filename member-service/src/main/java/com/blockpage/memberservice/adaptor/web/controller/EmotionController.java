package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.entity.EmotionEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.apispec.APIResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RequestEmotion;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/emotions")
public class EmotionController {

    @PostMapping
    private ResponseEntity<APIResponse> addEmotion(@RequestBody RequestEmotion requestEmotion) {
        EmotionEntity emotion = EmotionEntity.builder()
            .memberEntity(
                MemberEntity.builder().
                    email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("프로필이미지")
                    .role(Role.MEMBER)
                    .adult(false)
                    .build())
            .episodeId(requestEmotion.getEpisodeId())
            .commentId(requestEmotion.getEpisodeId())
            .emotion(requestEmotion.getEmotion())
            .build();
        return ResponseEntity.status(201).body(new APIResponse("댓글 반응이 등록되었습니다.", requestEmotion.getEmotion()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getEmotion(@RequestParam("episodeId") Long episodeId) {
        List<MemberView> memberViewList = new ArrayList<>();

        memberViewList.add(new MemberView(1L, 1L, (Boolean) true));
        memberViewList.add(new MemberView(2L, 3L, (Boolean) false));
        memberViewList.add(new MemberView(3L, 5L, (Boolean) true));

        return ResponseEntity.ok().body(new APIResponse<>(memberViewList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteEmotion(@PathVariable Long id) {
        return ResponseEntity.ok().body(new APIResponse("댓글 반응이 삭제 되엇습니다."));
    }
}
