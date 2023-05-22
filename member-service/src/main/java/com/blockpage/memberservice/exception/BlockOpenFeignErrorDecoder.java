package com.blockpage.memberservice.exception;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import feign.Response;
import feign.codec.ErrorDecoder;

public class BlockOpenFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (400 <= response.status() && response.status() < 500) {
            return new CustomException(BLOCK_SERVER_BAD_REQUEST.getMessage(), BLOCK_SERVER_BAD_REQUEST.getHttpStatus());
        } else if (500 <= response.status() && response.status() < 600) {
            return new CustomException(BLOCK_SERVER_UNAVAILABLE.getMessage(), BLOCK_SERVER_UNAVAILABLE.getHttpStatus());
        } else {
            return new CustomException(UNKNOWN_ERROR.getMessage(), UNKNOWN_ERROR.getHttpStatus());
        }
    }
}