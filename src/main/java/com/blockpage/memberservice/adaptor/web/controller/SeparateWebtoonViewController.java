package com.blockpage.memberservice.adaptor.web.controller;


import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.in.InterestUseCase.FindWebtoonQuery;
import com.blockpage.memberservice.application.port.out.dto.InterestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/view/v1/interest")
public class SeparateWebtoonViewController {

    private final InterestUseCase interestUseCase;

    @GetMapping()
    public ResponseEntity<ApiResponse<MemberView>> getInterest(@RequestHeader("memberId") String email, @RequestParam("webtoonId") Long webtoonId) {
        InterestDto interestDto = interestUseCase.findInterestWebtoonQuery(new FindWebtoonQuery(email, webtoonId));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView(interestDto.getChoice())));
    }

}
