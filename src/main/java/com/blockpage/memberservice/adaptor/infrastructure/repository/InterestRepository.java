package com.blockpage.memberservice.adaptor.infrastructure.repository;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    List<InterestEntity> findAllByMemberEmail(String memberEmail);

    Optional<InterestEntity> findByMemberEmailAndWebtoonId(String memberEmail,Long webtoonId);
}
