package kr.co.littleriders.backend.domain.station.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class StationException extends LittleRidersException {

    StationException(ShuttleErrorCode errorCode) {
        super(errorCode);
    }

    public static StationException from(ShuttleErrorCode errorCode) {
        return new StationException(errorCode);
    }

}
