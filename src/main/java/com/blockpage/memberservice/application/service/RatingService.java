package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.RatingUseCase;
import com.blockpage.memberservice.application.port.out.dto.RatingDto;
import com.blockpage.memberservice.application.port.out.port.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService implements RatingUseCase {

    private final RatingPort ratingPort;

    @Override
    public RatingDto postRatingQuery(PostQuery postQuery) {
        ratingPort.postRating(Rating.postRating(postQuery));
        return new RatingDto(postQuery.getRatings());
    }

    @Override
    public RatingDto findRatingQuery(FindQuery findQuery) {
        return new RatingDto(ratingPort.findRating(new Rating(findQuery)));
    }
}
