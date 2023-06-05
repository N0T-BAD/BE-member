package com.blockpage.memberservice.application.service;

import static com.blockpage.memberservice.exception.ErrorCode.UNKNOWN_ERROR;

import com.blockpage.memberservice.adaptor.infrastructure.message.sync.purchase.requestbody.RequestPurchase;
import com.blockpage.memberservice.application.port.in.MemberUseCase;
import com.blockpage.memberservice.application.port.out.dto.MemberDto;
import com.blockpage.memberservice.application.port.out.port.MemberPort;
import com.blockpage.memberservice.application.port.out.port.PurchasePort;
import com.blockpage.memberservice.domain.Member;
import com.blockpage.memberservice.exception.CustomException;
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
            purchasePort.postProfileSkin(signInQuery.getEmail(), new RequestPurchase());
        }
        return MemberDto.signIn(member);
    }

    @Override
    public MemberDto findMemberInfo(FindMemberQuery findMemberQuery) {
        if (findMemberQuery.getType().equals("detail")) {
            Member member = memberPort.findMemberInfo(Member.findMemberInfo(findMemberQuery));
            return MemberDto.fromMember(member);
        } else if (findMemberQuery.getType().equals("nickname")) {
            Member member = memberPort.findNickname(Member.findNickname(findMemberQuery));
            return MemberDto.fromCreatorNickname(member);
        } else {
            throw new CustomException(UNKNOWN_ERROR.getMessage(), UNKNOWN_ERROR.getHttpStatus());
        }
    }

    @Override
    public void updateMemberInfo(UpdateQuery updateQuery) throws IOException {
        memberPort.updateMemberInfo(Member.fromUpdateQuery(updateQuery));
    }
}