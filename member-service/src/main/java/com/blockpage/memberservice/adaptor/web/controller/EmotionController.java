package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.EmotionUseCase;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.DeleteQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.SaveQuery;
import com.blockpage.memberservice.application.port.in.RequestEmotion;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
@RequestMapping("/v1/emotions")
public class EmotionController {

    private final EmotionUseCase emotionUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> addEmotion(@RequestHeader("accessToken") String token,
        @RequestBody RequestEmotion requestEmotion) {
        MemberEntity memberEntity = MemberEntity.builder().id(1L).build();
        SaveQuery saveQuery = SaveQuery.toQuery(requestEmotion);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<MemberView>(new MemberView(emotionUseCase.saveEmotionQuery(saveQuery, memberEntity))));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberView>>> getEmotion(@RequestHeader("accessToken") String token,
        @Param("episodeId") Long episodeId) {
        MemberEntity memberEntity = MemberEntity.builder().id(1L).build();
        List<EmotionDto> emotionDtoList = emotionUseCase.findAllEmotionQuery(new FindQuery(episodeId), memberEntity);
        List<MemberView> memberViewList = emotionDtoList.stream()
            .map(emotionDto -> new MemberView(emotionDto))
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(memberViewList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberView>> deleteEmotion(@PathVariable Long id) {
        emotionUseCase.deleteEmotionQuery(new DeleteQuery(id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<MemberView>(new MemberView("댓글반응이 삭제되었습니다.")));
    }

}
