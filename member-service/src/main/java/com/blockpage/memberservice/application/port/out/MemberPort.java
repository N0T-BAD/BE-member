package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Member;
import java.io.IOException;

public interface MemberPort {

    Member findMember(Member member);

    void saveMember(Member member);

    Member findMemberInfo(Member member);

    void updateMemberInfo(Member member) throws IOException;

    Member updateCreatorNickname(Member member);

    void updateMemberRole(Member member);
}
