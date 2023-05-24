package com.blockpage.memberservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException customException) {
        log.error("에러코드: " +customException.getHttpstatus().toString()+", "+customException.getMessage());
        return ResponseEntity.status(customException.getHttpstatus()).body(customException.getMessage());
    }
}