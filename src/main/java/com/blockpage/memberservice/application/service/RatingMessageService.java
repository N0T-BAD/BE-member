package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.RatingAverageMessage;
import com.blockpage.memberservice.application.port.out.port.RatingMessagePort;
import com.blockpage.memberservice.application.port.out.port.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingMessageService {

    private final RatingMessagePort ratingMessagePort;
    private final RatingPort ratingPort;

    private final long FIXED_RATE_SECONDS = 5 * 60l;
    private final long FIXED_RATE_MILLISECOND = 5 * 60 * 1000; //5분
    private final long INITIAL_DELAY_MILLISECONDS = 3 * 1000;

    @Scheduled(fixedRate = FIXED_RATE_MILLISECOND, initialDelay = INITIAL_DELAY_MILLISECONDS)
    public void sendRatingAverage() {
        LocalDateTime now = LocalDateTime.now();
        List<Rating> ratingList = ratingPort.findRatingByCreateDate(now.minusSeconds(FIXED_RATE_SECONDS), now);
        Map<Long, RatingAverageMessage> ratingAverageMessageMap = ratingList.stream()
            .collect(Collectors.toMap(
                Rating::getEpisodeId,
                rating -> new RatingAverageMessage(rating.getEpisodeId(), rating.getWebtoonId(), rating.getRatings(), 1),
                RatingAverageMessage::sum
            ));

        for (Long episodeId : ratingAverageMessageMap.keySet()) {
            System.out.println("id = " + ratingAverageMessageMap.get(episodeId));
            ratingMessagePort.sendRatingAverage(ratingAverageMessageMap.get(episodeId));
        }
    }
}