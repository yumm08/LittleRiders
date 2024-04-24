package kr.co.littleriders.backend.global.error;


import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import kr.co.littleriders.backend.global.response.api.LittleRidersErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<LittleRidersErrorResponse> handleBaseException(LittleRidersException e) {
        final LittleRidersErrorCode littleRidersErrorCode = e.getErrorCode();
        return ResponseEntity.status(littleRidersErrorCode.getStatus()).body(LittleRidersErrorResponse.from(littleRidersErrorCode.getCode()));
    }
}
