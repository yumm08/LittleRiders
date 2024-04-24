package kr.co.littleriders.backend.domain.family.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum FamilyErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "부모님을 찾을수 없습니다");


    FamilyErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "FAMILY_" + code;
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
