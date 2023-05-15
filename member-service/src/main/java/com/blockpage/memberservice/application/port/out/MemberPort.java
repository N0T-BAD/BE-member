package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Member;

public interface MemberPort {

    MemberDto findMember(Member member);

    void saveMember(Member member);

    MemberDto findMemberInfo(Member member);
}
