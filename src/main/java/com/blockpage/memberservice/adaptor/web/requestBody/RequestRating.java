package com.blockpage.memberservice.adaptor.web.requestBody;

import lombok.Getter;

@Getter
public class RequestRating {

    private Long webtoonId;

    private Long episodeId;

    private Integer ratings;
}
