package kr.co.littleriders.backend.domain.station.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum StationErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "뭘 넣어야 할까요;;;"); //TODO : 진짜 뭐넣지


    StationErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "STATION_" + code;
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
