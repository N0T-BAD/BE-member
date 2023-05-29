package com.blockpage.memberservice.application.service;

import static java.util.stream.Collectors.mapping;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.RatingAverageMessage;
import com.blockpage.memberservice.application.port.out.port.RatingMessagePort;
import com.blockpage.memberservice.application.port.out.port.RatingPort;
import com.blockpage.memberservice.domain.Rating;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingMessageService {

    private RatingMessagePort ratingMessagePort;
    private RatingPort ratingPort;

    private final long FIXED_RATE_SECONDS = 10l;
    private final long FIXED_RATE_MILLISECOND = 10 * 1000;
    private final long INITIAL_DELAY_MILLISECONDS = 3 * 1000;

    @Scheduled(fixedRate = FIXED_RATE_MILLISECOND, initialDelay = INITIAL_DELAY_MILLISECONDS)
    public void sendRatingAverage() {
        LocalDateTime now = LocalDateTime.now();
        List<Rating> ratingList = ratingPort.findRatingByCreateDate(now.minusSeconds(FIXED_RATE_SECONDS), now);
        Map<Long, RatingAverageMessage> ratingAverageMessageMap = ratingList.stream();
    }
}