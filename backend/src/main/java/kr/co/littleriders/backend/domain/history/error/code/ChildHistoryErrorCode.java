package kr.co.littleriders.backend.domain.history.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ChildHistoryErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "아이의 업데이트 기록을 찾을수 없습니다");


    ChildHistoryErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "CHILD_HISTORY_" + code;
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
