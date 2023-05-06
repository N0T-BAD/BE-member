package com.blockpage.memberservice.application.in;

import lombok.Getter;

@Getter
public class RequestInterest {

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

}