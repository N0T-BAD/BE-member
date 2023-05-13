package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.RatingEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.RatingRepository;
import com.blockpage.memberservice.application.port.out.RatingPort;

import com.blockpage.memberservice.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingAdaptor implements RatingPort {

    private final RatingRepository ratingRepository;

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(RatingEntity.builder()
            .memberEntity(rating.getMemberEntity())
            .episodeId(rating.getEpisodeId())
            .ratings(rating.getRatings())
            .build());
    }
}