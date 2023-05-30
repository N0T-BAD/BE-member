package com.blockpage.memberservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.memberservice.domain.Rating;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rating")
public class RatingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private Long episodeId;

    @Column
    private Integer ratings;

    public static RatingEntity fromRating(Rating rating) {
        return RatingEntity.builder()
            .memberEmail(rating.getMemberEmail())
            .episodeId(rating.getEpisodeId())
            .ratings(rating.getRatings())
            .build();
    }

}
