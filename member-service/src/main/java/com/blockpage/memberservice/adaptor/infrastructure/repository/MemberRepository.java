package com.blockpage.memberservice.adaptor.infrastructure.repository;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByKakaoId(Long kakaoId);

    Optional<MemberEntity> findByCreatorNickname(String creatorNickname);

}
