package com.blockpage.memberservice.adaptor.web;

import com.blockpage.memberservice.adaptor.infrastructure.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.InterestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/interests")
@Slf4j
public class MockInterestsController {

    private final InterestRepository interestRepository;

    @GetMapping
    public ResponseEntity getInterests(@RequestParam("member_id") Long memberId){
        List<InterestEntity> interestEntityList = interestRepository.findAllByMemberEntityId(memberId);
        log.info(interestEntityList.toString());
        List<InterestView> interestViewList = interestRepository.findAllByMemberEntityId(memberId).stream()
                .map(interestEntity -> InterestView.builder()
                        .id(interestEntity.getId())
                        .webtoonId(interestEntity.getWebtoonId())
                        .webtoonThumbnail(interestEntity.getWebtoonThumbnail())
                        .creator(interestEntity.getCreator())
                        .illustrator(interestEntity.getIllustrator())
                        .genre(interestEntity.getGenre())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(interestViewList);
    }


}
