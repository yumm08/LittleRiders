package kr.co.littleriders.backend.domain.child.error.exception;


import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ChildException extends LittleRidersException {


    ChildException(ChildErrorCode errorCode) {
        super(errorCode);
    }

    public static ChildException from(ChildErrorCode errorCode) {
        return new ChildException(errorCode);
    }

}
