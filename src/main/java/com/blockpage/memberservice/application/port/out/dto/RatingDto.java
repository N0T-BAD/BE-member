package com.blockpage.memberservice.application.port.out.dto;

import lombok.Getter;

@Getter
public class RatingDto {

    private Integer ratings;

    public RatingDto(Integer ratings) {
        this.ratings = ratings;
    }
}