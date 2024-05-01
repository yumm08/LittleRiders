package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleLocationHistoryErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleLocationHistoryException extends LittleRidersException {

    ShuttleLocationHistoryException(ShuttleLocationHistoryErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleLocationHistoryException from(ShuttleLocationHistoryErrorCode errorCode) {
        return new ShuttleLocationHistoryException(errorCode);
    }

}
