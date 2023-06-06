package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Member;
import java.io.IOException;

public interface MemberPort {

    Member signInMember(Member member);

    Member findMemberInfo(Member member);

    Member findAuthorInfo(Member member);

    void findNickname(Member member);

    void updateMemberInfo(Member member) throws IOException;
}
