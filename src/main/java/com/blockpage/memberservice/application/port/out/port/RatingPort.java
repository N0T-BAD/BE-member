package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Rating;

public interface RatingPort {

    void postRating(Rating rating);

    Rating findRating(Rating rating);
}