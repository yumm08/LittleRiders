package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;


public enum AuthErrorCode implements LittleRidersErrorCode {


    JWT_EXPIRED(HttpStatus.BAD_REQUEST, "001", "토큰이 만료되었습니다."),
    JWT_NOT_SUPPORT(HttpStatus.BAD_REQUEST, "002", "지원하지 않는 토큰입니다."),
    JWT_KET_NOT_VALID(HttpStatus.BAD_REQUEST, "003", "알 수 없는 오류가 발생했습니다.");


    AuthErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "AUTH_" + code;
        this.message = message;
    }

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
