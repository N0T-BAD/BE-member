package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Rating;

public interface RatingPort {

    void postRating(Rating rating);

    Rating findRating(Rating rating);
}