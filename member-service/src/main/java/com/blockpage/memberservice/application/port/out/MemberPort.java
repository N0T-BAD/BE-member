package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import java.util.Optional;

public interface MemberPort {

    Optional<MemberEntity> findMember(Long kakaoId);
}
