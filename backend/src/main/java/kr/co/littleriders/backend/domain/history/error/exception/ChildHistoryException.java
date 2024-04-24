package kr.co.littleriders.backend.domain.history.error.exception;

import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.history.error.code.ChildHistoryErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ChildHistoryException extends LittleRidersException {

    ChildHistoryException(ChildHistoryErrorCode errorCode) {
        super(errorCode);
    }

    public static ChildHistoryException from(ChildHistoryErrorCode errorCode) {
        return new ChildHistoryException(errorCode);
    }

}
