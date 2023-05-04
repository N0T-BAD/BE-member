package com.blockpage.memberservice.adaptor.web;

import com.blockpage.memberservice.adaptor.infrastructure.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.MemberRepository;
import com.blockpage.memberservice.adaptor.infrastructure.Role;
import com.blockpage.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/members")
public class MockMemberController {

    private final MemberRepository memberRepository;

//    @PostMapping("")
//    public ResponseEntity joinMember(@RequestBody Member member){
//        MemberEntity memberEntity = MemberEntity.builder()
//                .email(member.getEmail())
//                .nickname(member.getNickname())
//                .profileImage(member.getProfileImage())
//                .gender(member.getGender())
//                .role(Role.MEMBER)
//                .adult(false)
//                .build();
//        memberRepository.save(memberEntity);
//        return ResponseEntity.ok().body(memberEntity);
//    }

    @GetMapping
    public ResponseEntity<?> getMember(@RequestParam("email") String email) {
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);
        if (memberEntity.isPresent()) {
            MemberView memberView = MemberView.builder()
                    .nickname(memberEntity.get().getNickname() !=null ? memberEntity.get().getNickname():"회원")
                    .profileImage(memberEntity.get().getProfileImage() != null ? memberEntity.get().getProfileImage() : "없음")
                    .profileSkin(memberEntity.get().getProfileSkin() != null ? memberEntity.get().getProfileSkin() : "구매해주세요")
                    .role(memberEntity.get().getRole())
                    .build();
            return ResponseEntity.ok().body(memberView);
        }else{
            return null;
        }
    }
}
