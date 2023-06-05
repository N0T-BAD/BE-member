package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message.InterestCountMessage;
import com.blockpage.memberservice.application.port.out.port.InterestMessagePort;
import com.blockpage.memberservice.application.port.out.port.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterestMessageService {

    private final InterestMessagePort interestMessagePort;
    private final InterestPort interestPort;

    private final Integer INTEREST_LIKE_COUNT = 1;
    private final Integer INTEREST_DISLIKE_COUNT = -1;

    private final long FIXED_RATE_SECONDS = 5 * 60l;
    private final long FIXED_RATE_MILLISECOND = 5 * 60 * 1000; //5분
    private final long INITIAL_DELAY_MILLISECONDS = 3 * 1000;

    @Scheduled(fixedRate = FIXED_RATE_MILLISECOND, initialDelay = INITIAL_DELAY_MILLISECONDS)
    public void sendInterestMessage() {
        LocalDateTime now = LocalDateTime.now();
        List<Interest> interestList = interestPort.findInterestByUpdateDate(now.minusSeconds(FIXED_RATE_SECONDS), now);
        Map<Long, InterestCountMessage> postInterestCountMessageMap = interestList.stream()
            .filter(interest -> interest.getErase() == Boolean.FALSE)
            .collect(Collectors.toMap(
                Interest::getWebtoonId,
                interest -> new InterestCountMessage(interest.getWebtoonId(), INTEREST_LIKE_COUNT),
                InterestCountMessage::sum
            ));
        Map<Long, InterestCountMessage> deleteInterestCountMessageMap = interestList.stream()
            .filter(interest -> interest.getErase() == Boolean.TRUE)
            .collect(Collectors.toMap(
                Interest::getWebtoonId,
                interest -> new InterestCountMessage(interest.getWebtoonId(), INTEREST_DISLIKE_COUNT),
                InterestCountMessage::sum
            ));
        Map<Long, InterestCountMessage> totalInterestCountMessageMap = Stream.of(postInterestCountMessageMap, deleteInterestCountMessageMap)
            .flatMap(message -> message.entrySet().stream())
            .collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    InterestCountMessage::sum));

        for (Long webtoonId : totalInterestCountMessageMap.keySet()) {
            System.out.println("id = " + totalInterestCountMessageMap.get(webtoonId));
            interestMessagePort.sendInterestCount(totalInterestCountMessageMap.get(webtoonId));
        }
    }


}