package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.RATINGS_NOT_EXIST;

import com.blockpage.memberservice.adaptor.infrastructure.entity.RatingEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.RatingRepository;
import com.blockpage.memberservice.application.port.out.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import com.blockpage.memberservice.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingAdaptor implements RatingPort {

    private final RatingRepository ratingRepository;

    @Override
    public void postRating(Rating rating) {
        ratingRepository.save(RatingEntity.fromRating(rating));
    }

    @Override
    public Rating findRating(Rating rating) {
        Optional<RatingEntity> ratingEntity = ratingRepository.findByMemberEmailAndEpisodeId(rating.getMemberEmail(),
            rating.getEpisodeId());
        if (ratingEntity.isPresent()) {
            return new Rating(ratingEntity.get().getRatings());
        } else {
            throw new CustomException(RATINGS_NOT_EXIST.getMessage(), RATINGS_NOT_EXIST.getHttpStatus());
        }

    }
}