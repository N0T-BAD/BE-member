package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestCountMessage {

    private Long webtoonId;
    private Integer interestCount;

    public static InterestCountMessage initMessage(Long webtoonId, Integer interestCount) {
        return InterestCountMessage.builder()
            .webtoonId(webtoonId)
            .interestCount(interestCount)
            .build();
    }
}
