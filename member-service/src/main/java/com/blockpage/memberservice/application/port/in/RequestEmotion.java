package com.blockpage.memberservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestEmotion {

    private Long episodeId;

    private Long commentId;

    private Boolean emotion;
}
