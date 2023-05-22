package com.blockpage.memberservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //purchase-service
    PURCHASE_SERVER_BAD_REQUEST("구매 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.BAD_REQUEST),
    PURCHASE_SERVER_UNAVAILABLE("구매 서비스 이용이 불가능합니다.", HttpStatus.GATEWAY_TIMEOUT),


    //block-service
    BLOCK_SERVER_BAD_REQUEST("블럭 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.BAD_REQUEST),
    BLOCK_SERVER_UNAVAILABLE("블럭 서비스 이용이 불가능합니다.", HttpStatus.GATEWAY_TIMEOUT),

    //global
    UNKNOWN_ERROR("알수 없는 에러가 발생했습니다.", HttpStatus.NO_CONTENT);
    private final String message;
    private final HttpStatus httpStatus;
}