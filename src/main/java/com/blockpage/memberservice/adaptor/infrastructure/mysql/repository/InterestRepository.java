package com.blockpage.memberservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.InterestEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    List<InterestEntity> findAllByMemberEmail(String memberEmail);

    Optional<InterestEntity> findByMemberEmailAndWebtoonIdAndEraseFalse(String memberEmail, Long webtoonId);

    Optional<InterestEntity> findByMemberEmailAndWebtoonId(String memberEmail, Long webtoonId);

    List<InterestEntity> findAllByUpdateTimeBetween(LocalDateTime start, LocalDateTime end);
}
