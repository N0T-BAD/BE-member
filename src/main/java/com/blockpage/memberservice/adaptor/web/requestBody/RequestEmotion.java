package com.blockpage.memberservice.adaptor.web.requestBody;

import lombok.Getter;

@Getter
public class RequestEmotion {

    private Long episodeId;

    private Long commentId;

    private Boolean emotion;
}
