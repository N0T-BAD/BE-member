package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.out.MemberDto;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Override
    public MemberDto findMemberKakao(FindQuery findQuery) {
        Member member = new Member(findQuery.getKakaoId());
        return memberPort.findMember(member);
    }

    @Override
    public MemberDto findMemberinfo(FindMemberQuery findMemberQuery) {
        Member member = new Member(findMemberQuery);
        return memberPort.findMemberInfo(member);
    }
}
