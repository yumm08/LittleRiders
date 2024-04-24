package kr.co.littleriders.backend.domain.history.error.exception;

import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.history.error.code.FamilyHistoryErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class FamilyHistoryException extends LittleRidersException {

    FamilyHistoryException(FamilyHistoryErrorCode errorCode) {
        super(errorCode);
    }

    public static FamilyHistoryException from(FamilyHistoryErrorCode errorCode) {
        return new FamilyHistoryException(errorCode);
    }

}
