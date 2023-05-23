package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.out.MemberDto;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.application.port.out.PurchasePort;
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
    private final PurchasePort purchasePort;

    @Override
    public MemberDto signInMember(SignInQuery signInQuery) {
        Member member = memberPort.signInMember(Member.signInMember(signInQuery));
        if (member.getSignUp()) {
            purchasePort.postProfileSkin(new RequestPurchase(signInQuery.getEmail()));
        }
        return MemberDto.signIn(member);
    }

    @Override
    public MemberDto findMemberInfo(FindMemberQuery findMemberQuery) {
        return MemberDto.fromMember(memberPort.findMemberInfo(Member.findMemberInfo(findMemberQuery)));
    }

    @Override
    public void updateMemberInfo(UpdateQuery updateQuery) throws IOException {
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