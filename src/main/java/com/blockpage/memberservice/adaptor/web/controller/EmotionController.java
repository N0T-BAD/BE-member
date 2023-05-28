package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.EmotionUseCase;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.DeleteQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.EmotionUseCase.PostQuery;
import com.blockpage.memberservice.adaptor.web.requestBody.RequestEmotion;
import com.blockpage.memberservice.application.port.out.dto.EmotionDto;
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
@RequestMapping("/member-service/v1/emotions")
public class EmotionController {

    private final EmotionUseCase emotionUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> addEmotion(@RequestHeader String email,
        @RequestBody RequestEmotion requestEmotion) {
        EmotionDto emotionDto = emotionUseCase.postEmotionQuery(PostQuery.toQuery(email, requestEmotion));
        if (emotionDto.getEmotion() != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<MemberView>(new MemberView("댓글 반응이 생성되엇습니다.", emotionDto.getEmotion())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<MemberView>(new MemberView("댓글이 삭제 되었습니다.")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberView>>> getEmotion(@RequestHeader String email,
        @Param("episodeId") Long episodeId) {
        List<EmotionDto> emotionDtoList = emotionUseCase.findAllEmotionQuery(new FindQuery(email, episodeId));
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
