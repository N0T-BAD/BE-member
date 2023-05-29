package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.RatingEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.RatingRepository;
import com.blockpage.memberservice.application.port.out.port.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import com.blockpage.memberservice.exception.CustomException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public List<Rating> findRatingByCreateDate(LocalDateTime start, LocalDateTime end) {
        List<Rating> ratingList = ratingRepository.findAllByRegisterTimeBetween(start, end).stream()
            .map(Rating::fromEntity)
            .collect(Collectors.toList());
        return ratingList;
    }
}