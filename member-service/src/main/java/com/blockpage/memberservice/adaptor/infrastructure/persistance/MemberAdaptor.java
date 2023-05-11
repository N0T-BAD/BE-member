package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
import com.blockpage.memberservice.application.port.out.MemberPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAdaptor implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Optional<MemberEntity> findMember(Long kakaoId) {
        Optional<MemberEntity> memberEntity = memberRepository.findByKakaoId(kakaoId);
        return memberEntity;
    }s
}
