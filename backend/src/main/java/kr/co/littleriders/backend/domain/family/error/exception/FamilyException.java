package kr.co.littleriders.backend.domain.family.error.exception;

import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class FamilyException extends LittleRidersException {

    FamilyException(FamilyErrorCode errorCode) {
        super(errorCode);
    }

    public static FamilyException from(FamilyErrorCode errorCode) {
        return new FamilyException(errorCode);
    }

}
