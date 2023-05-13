package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.RatingUseCase;
import com.blockpage.memberservice.application.port.out.RatingDto;
import com.blockpage.memberservice.application.port.out.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService implements RatingUseCase {

    private final RatingPort ratingPort;

    @Override
    public RatingDto saveRatingQuery(SaveQuery saveQuery, MemberEntity memberEntity) {
        Rating rating = Rating.addRating(saveQuery, memberEntity);
        ratingPort.saveRating(rating);
        return RatingDto.toQuery(saveQuery);
    }
}
