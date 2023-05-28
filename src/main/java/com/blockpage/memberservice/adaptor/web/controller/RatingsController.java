package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.RatingUseCase;
import com.blockpage.memberservice.application.port.in.RatingUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.RatingUseCase.PostQuery;
import com.blockpage.memberservice.adaptor.web.requestBody.RequestRating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/v1/ratings")
@Slf4j
public class RatingsController {

    private final RatingUseCase ratingUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberView>> addRating(@RequestHeader String email,
        @RequestBody RequestRating requestRating) {
        ratingUseCase.postRatingQuery(PostQuery.toQuery(email, requestRating));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new MemberView("평점 등록 되었습니다.")));
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<ApiResponse<MemberView>> getInterest(@RequestHeader String email,
        @PathVariable Long episodeId) {
        Integer ratings = ratingUseCase.findRatingQuery(new FindQuery(email, episodeId)).getRatings();
        return ResponseEntity.ok().body(new ApiResponse<>(new MemberView(ratings)));
    }

}

