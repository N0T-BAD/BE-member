package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.in.InterestUseCase.DeleteQuery;
import com.blockpage.memberservice.application.port.in.InterestUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.InterestUseCase.SaveQuery;
import com.blockpage.memberservice.application.port.in.RequestInterest;
import java.util.List;
import java.util.stream.Collectors;
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
    public ResponseEntity<ApiResponse<MemberView>> addInterst(@RequestBody RequestInterest requestInterest,
        @RequestHeader("accessToken") String token) {
        //TODO 헤더 토큰으로 멤버 가져오기 구현예정(토큰>카카오토큰정보조회>카카오아이디받아오기>멤버가져오기)
        MemberEntity memberEntity = MemberEntity.builder()
            .id(1L)
            .build();
        SaveQuery saveQuery = SaveQuery.toQuery(requestInterest);
        interestUseCase.saveInterestQuery(saveQuery, memberEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<MemberView>(new MemberView("찜생성 되었습니다.")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberView>>> getIngerest(@RequestHeader("accessToken") String token) {
        FindQuery findQuery = FindQuery.toQuery(1L);
        List<MemberView> memberViewList = interestUseCase.findInterestQuery(findQuery).stream()
            .map(interestDto -> new MemberView(interestDto))
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<MemberView>>(memberViewList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberView>> deleteInterest(@PathVariable Long id) {
        DeleteQuery deleteQuery = DeleteQuery.toQuery(id);
        interestUseCase.deleteInterestQuery(deleteQuery);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<MemberView>(new MemberView("찜삭제 되었습니다.")));
    }
}