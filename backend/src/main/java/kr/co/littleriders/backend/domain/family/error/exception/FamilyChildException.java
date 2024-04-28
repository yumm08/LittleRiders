package kr.co.littleriders.backend.domain.family.error.exception;

import kr.co.littleriders.backend.domain.family.error.code.FamilyChildErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class FamilyChildException extends LittleRidersException {

    FamilyChildException(FamilyChildErrorCode errorCode) {
        super(errorCode);
    }

    public static FamilyChildException from(FamilyChildErrorCode errorCode) {
        return new FamilyChildException(errorCode);
    }

}
