package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Rating;

public interface RatingPort {

    void saveRating(Rating rating);
}