package com.blockpage.memberservice.application.port.out.dto;

import com.blockpage.memberservice.domain.Rating;
import lombok.Getter;

@Getter
public class RatingDto {

    private Boolean choice;

    private Integer ratings;


    public RatingDto(Rating rating) {
        this.choice = rating.getChoice();
        this.ratings = rating.getRatings();
    }

    public RatingDto(Integer ratings) {
        this.ratings = ratings;
    }
}