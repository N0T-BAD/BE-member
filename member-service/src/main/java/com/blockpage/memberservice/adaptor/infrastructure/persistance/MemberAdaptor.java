package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
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
    public Member findMember(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByKakaoId(member.getKakaoId());
        if (memberEntity.isPresent()) {
            Member member1 = Member.fromMemberEntity(memberEntity.get());
            return member1;
        }
        return null;
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(MemberEntity.fromMember(member));
    }

    @Override
    public Member findMemberInfo(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        if (memberEntity.isPresent()) {
            Member member1 = Member.fromMemberEntity(memberEntity.get());
            return member1;
        }
        return null;
    }

    @Override
    public void updateMemberInfo(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        if (memberEntity.get().getProfileImage().equals(member.getProfileImage())) {
            MemberEntity updateMemberEntity = MemberEntity.updateMember(memberEntity.get(), member);
            memberRepository.save(updateMemberEntity);

        } else {
            //TODO 프로필이미지 변경시 S3 이미지 저장 및 주소 가져오기 생성
        }
    }
}
