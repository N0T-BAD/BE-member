package com.blockpage.memberservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.EmotionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Long> {

    Optional<EmotionEntity> findByMemberEmailAndEpisodeIdAndCommentId(String memberEmail, Long episodeId, Long commentId);

    Optional<EmotionEntity> findAllByMemberEmailAndCommentId(String memberEmail, Long commentId);
}
