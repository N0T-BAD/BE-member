package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RatingUseCase;
import com.blockpage.memberservice.application.port.in.RatingUseCase.SaveQuery;
import com.blockpage.memberservice.application.port.in.RequestRating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ratings")
@Slf4j
public class RatingsController {

    private final RatingUseCase ratingUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addRating(@RequestBody RequestRating requestRating,
        @RequestHeader("accessToken") String token) {
        MemberEntity memberEntity = MemberEntity.builder().id(1L).build();
        SaveQuery saveQuery = SaveQuery.toQuery(requestRating);
        ratingUseCase.saveRatingQuery(saveQuery, memberEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<String>("평점 등록 되었습니다."));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getInterest(@RequestParam("episodeId") Long episodeId) {
        return ResponseEntity.ok().body(new ApiResponse(new MemberView(8)));
    }

}
