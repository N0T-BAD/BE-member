package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.out.MemberDto;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.domain.Member;
import java.io.IOException;
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
        MemberDto memberDto = MemberDto.fromMember(memberPort.findMember(member));
        return memberDto;
    }

    @Override
    public MemberDto findMemberinfo(FindMemberQuery findMemberQuery) {
        Member member = Member.findMember(findMemberQuery);
        if (findMemberQuery.getType().equals("detail")) {
            if (findMemberQuery.getId() != null) {
                return MemberDto.fromMember(memberPort.findMemberInfo(member));
            } else {
                throw new RuntimeException("id 값이 필수로 필요 합니다.");
            }
        } else if (findMemberQuery.getType().equals("nickname")) {
            return MemberDto.fromMember(memberPort.updateCreatorNickname(member));
        } else {
            throw new RuntimeException("잘못된 접근입니다.");
        }
    }

    @Override
    public void updateMemberInfo(UpdateQuery updateQuery) throws IOException{
        if (updateQuery.getType().equals("member")) {
            Member member = Member.fromUpdateQuery(updateQuery);
            memberPort.updateMemberInfo(member);
        } else if (updateQuery.getType().equals("author")) {
            Member member = Member.updateCreator(updateQuery);
            memberPort.updateMemberRole(member);
        } else {
            throw new RuntimeException("잘못된 접근입니다.");
        }
    }
}
