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
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RatingAdaptor implements RatingPort {

    private final RatingRepository ratingRepository;

    @Override
    @Transactional
    public void postRating(Rating rating) {
        ratingRepository.findByMemberEmailAndEpisodeId(rating.getMemberEmail(),
            rating.getEpisodeId()).ifPresentOrElse(ratingEntity -> {
                throw new CustomException(RATINGS_ALREADY_EXIST.getMessage(), RATINGS_ALREADY_EXIST.getHttpStatus());
            },
            () -> {
                ratingRepository.save(RatingEntity.fromRating(rating));
            }
        );
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<Rating> findRatingByCreateDate(LocalDateTime start, LocalDateTime end) {
        List<Rating> ratingList = ratingRepository.findAllByRegisterTimeBetween(start, end).stream()
            .map(Rating::fromEntity)
            .collect(Collectors.toList());
        return ratingList;
    }
}