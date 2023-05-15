package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
import com.blockpage.memberservice.application.port.out.MemberDto;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAdaptor implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberDto findMember(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByKakaoId(member.getKakaoId());
        if (memberEntity.isPresent()) {
            MemberDto memberDto = MemberDto.fromMemberEntity(memberEntity.get());
            return memberDto;
        }
        return null;
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(MemberEntity.fromMember(member));
    }

    @Override
    public MemberDto findMemberInfo(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        if (memberEntity.isPresent()) {
            MemberDto memberDto = MemberDto.fromMemberEntity(memberEntity.get());
            return memberDto;
        }
        return null;
    }
}
