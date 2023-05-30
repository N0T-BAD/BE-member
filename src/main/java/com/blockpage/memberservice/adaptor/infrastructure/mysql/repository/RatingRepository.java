package com.blockpage.memberservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.RatingEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    Optional<RatingEntity> findByMemberEmailAndEpisodeId(String memberEmail, Long episodeId);

    List<RatingEntity> findAllByRegisterTimeBetween(LocalDateTime start, LocalDateTime end);
}
