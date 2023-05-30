package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Rating;
import java.time.LocalDateTime;
import java.util.List;

public interface RatingPort {

    void postRating(Rating rating);

    Rating findRating(Rating rating);

    List<Rating> findRatingByCreateDate(LocalDateTime start, LocalDateTime end);
}