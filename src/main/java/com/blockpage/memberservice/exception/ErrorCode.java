package com.blockpage.memberservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //purchase-service
    PURCHASE_SERVER_BAD_REQUEST("구매 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.BAD_REQUEST),
    PURCHASE_SERVER_UNAVAILABLE("구매 서비스 이용이 불가능합니다.", HttpStatus.BAD_REQUEST),

    //member-service
    NICKNAME_ALREADY_EXIST("이미 등록된 닉네임 입니다.", HttpStatus.CONFLICT),
    ID_NOT_EXIST("존재하지 않는 회원입니다..", HttpStatus.BAD_REQUEST),
    PASSWORD_DISCORD("비밀번호가 일치하지 않습니다", HttpStatus.BAD_REQUEST),


    //block-service
    BLOCK_SERVER_BAD_REQUEST("블럭 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.BAD_REQUEST),
    BLOCK_SERVER_UNAVAILABLE("블럭 서비스 이용이 불가능합니다.", HttpStatus.BAD_REQUEST),

    //Emotion
    EMOTION_ALREADY_POST("이미 반응한 댓글입니다.", HttpStatus.CONFLICT),

    //Interest
    INTEREST_NOT_EXIST("존재하지 않는 찜 입니다.", HttpStatus.CONFLICT),

    //attendance
    ATTENDANCE_ALREADY_POST("이미 출석체크 하셨습니다.", HttpStatus.CONFLICT),

    //Ratings
    RATINGS_ALREADY_EXIST("이미 평점등록 하였습니다.", HttpStatus.BAD_REQUEST),
    RATINGS_NOT_EXIST("평점이 등록되지 않았습니다.", HttpStatus.BAD_REQUEST),

    //Admin
    SESSION_EXPIRE("세션이 만료되었습니다.", HttpStatus.BAD_REQUEST),

    //kafka
    SERVER_ERROR("서버 에러", HttpStatus.CONFLICT),

    //global
    UNKNOWN_ERROR("알수 없는 에러가 발생했습니다.", HttpStatus.BAD_REQUEST),
    MEMBER_LIMIT_SERVICE("회원에 한해 이용 가능한 서비스 입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}