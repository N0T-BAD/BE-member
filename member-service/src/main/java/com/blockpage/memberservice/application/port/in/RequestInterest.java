package com.blockpage.memberservice.application.port.in;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class RequestInterest {

    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

}