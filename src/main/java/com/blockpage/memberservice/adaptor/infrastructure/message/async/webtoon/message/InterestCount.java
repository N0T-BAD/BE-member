package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestCount {

    private Long webtoonId;
    private Integer interestCount;

    public static InterestCount initMessage(Long webtoonId, Integer interestCount) {
        return InterestCount.builder()
            .webtoonId(webtoonId)
            .interestCount(interestCount)
            .build();
    }
}
