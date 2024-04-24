package kr.co.littleriders.backend.domain.pending.error.exception;

import kr.co.littleriders.backend.domain.history.error.code.BoardDropHistoryErrorCode;
import kr.co.littleriders.backend.domain.pending.error.code.PendingErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class PendingException extends LittleRidersException {

    PendingException(PendingErrorCode errorCode) {
        super(errorCode);
    }

    public static PendingException from(PendingErrorCode errorCode) {
        return new PendingException(errorCode);
    }

}
