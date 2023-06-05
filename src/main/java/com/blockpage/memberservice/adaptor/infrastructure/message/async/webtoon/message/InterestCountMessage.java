package com.blockpage.memberservice.adaptor.infrastructure.message.async.webtoon.message;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.exception.CustomException;
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

    public static InterestCountMessage sendMessage(Long webtoonId, Integer interestCount) {
        return InterestCountMessage.builder()
            .webtoonId(webtoonId)
            .interestCount(interestCount)
            .build();
    }

    public InterestCountMessage sum(InterestCountMessage message) {
        if (this.webtoonId.equals(message.webtoonId)) {
            return new InterestCountMessage(webtoonId, this.interestCount + message.interestCount);
        } else {
            throw new CustomException(SERVER_ERROR.getMessage(),SERVER_ERROR.getHttpStatus());
        }
    }
}
