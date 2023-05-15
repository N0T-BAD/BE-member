package com.blockpage.memberservice.adaptor.web.controller;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.adaptor.web.view.ApiResponse;
import com.blockpage.memberservice.adaptor.web.view.MemberView;
import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.RequestMember;
import com.blockpage.memberservice.application.port.out.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@Slf4j
public class MemberController {

    private final MemberUseCase memberUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse> joinMember(@RequestBody RequestMember requestMember) {
        MemberEntity memberEntity = MemberEntity.builder()
            .email(requestMember.getEmail())
            .nickname(requestMember.getNickname() != null ? requestMember.getNickname() : "닉네임을 설정해 보세요")
            .profileImage(requestMember.getProfileImage() != null ? requestMember.getProfileImage() : "기본스킨제공")
            .gender(requestMember.getGender() != null ? requestMember.getGender() : "성별 등록되지 않음")
            .role(Role.MEMBER)
            .adult(false)
            .build();
        log.info(memberEntity.toString());
        return ResponseEntity.ok().body(new ApiResponse("회원가입이 완료되었습니다."));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateMember(@RequestParam("type") String type, @RequestBody RequestMember requestMember) {
        switch (type) {
            case "nickname": {
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname(requestMember.getNickname())
                    .profileImage("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg")
                    .role(Role.MEMBER)
                    .adult(false)
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse(requestMember.getNickname()));
            }
            case "profileImage": {
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname("고은")
                    .profileImage(requestMember.getProfileImage())
                    .role(Role.MEMBER)
                    .adult(false)
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse(requestMember.getProfileImage()));
            }
            case "profileSkin": {
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg")
                    .profileSkin(requestMember.getProfileSkin())
                    .role(Role.MEMBER)
                    .adult(false)
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse(requestMember.getProfileSkin()));
            }
            case "author": {
                String addCreatorNickname = "인정";
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg")
                    .role(Role.AUTHOR)
                    .creatorNickname(requestMember.getCreatorNickname())
                    .adult(false)
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse(requestMember.getCreatorNickname()));
            }
            case "authorNickname": {
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg")
                    .role(Role.AUTHOR)
                    .adult(false)
                    .creatorNickname(requestMember.getCreatorNickname())
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse(requestMember.getCreatorNickname()));
            }
            case "adult": {
                MemberEntity memberEntity = MemberEntity.builder()
                    .email("abc@naver.com")
                    .nickname("고은")
                    .profileImage("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg")
                    .role(Role.MEMBER)
                    .adult(true)
                    .build();
                log.info(memberEntity.toString());
                return ResponseEntity.ok().body(new ApiResponse("성인으로 인증되었습니다."));
            }
            default: {
                return ResponseEntity.ok().body(new ApiResponse("회원정보 수정이 이루어지지 않았습니다."));
            }
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MemberView>> getMember(@RequestHeader("accessToken") String token) {
        Long id = 1L;
        FindMemberQuery findMemberQuery = new FindMemberQuery(id);
        MemberDto memberDto = memberUseCase.findMemberinfo(findMemberQuery);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new MemberView(memberDto)));
    }
//    @GetMapping
//    public ResponseEntity<ApiResponse> getMember() {
//        MemberView memberView = new MemberView("고은",
//            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", null,
//            Role.MEMBER, null);
//        return ResponseEntity.ok().body(new ApiResponse(memberView));
//    }
}
