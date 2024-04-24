package kr.co.littleriders.backend.domain.academy.error.exception;

import kr.co.littleriders.backend.domain.academy.error.code.AcademyFamilyErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class AcademyFamilyException extends LittleRidersException {


    AcademyFamilyException(AcademyFamilyErrorCode errorCode) {
        super(errorCode);

    }

    public static AcademyFamilyException from(AcademyFamilyErrorCode errorCode) {
        return new AcademyFamilyException(errorCode);
    }

}
