package com.blockpage.memberservice.adaptor.infrastructure.repository;

import com.blockpage.memberservice.adaptor.infrastructure.entity.EmotionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Long> {

    Optional<EmotionEntity> findByMemberEntityIdAndEpisodeIdAndCommentId(Long memberId, Long episodeId, Long commentId);

    List<EmotionEntity> findAllByMemberEntityIdAndEpisodeId(Long memberId, Long episodeId);
}
