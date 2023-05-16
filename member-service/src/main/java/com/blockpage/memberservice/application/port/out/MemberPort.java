package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Member;

public interface MemberPort {

    Member findMember(Member member);

    void saveMember(Member member);

    Member findMemberInfo(Member member);

    void updateMemberInfo(Member member);
}
