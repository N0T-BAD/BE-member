package com.blockpage.memberservice.adaptor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InterestRepository extends JpaRepository<InterestEntity,Long> {
    List<InterestEntity> findAllByMemberEntityId(Long memberId);
}
