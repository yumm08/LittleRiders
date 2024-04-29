package kr.co.littleriders.backend.global.error;


import jakarta.validation.ConstraintViolationException;
import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import kr.co.littleriders.backend.global.response.api.LittleRidersErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<LittleRidersErrorResponse> handleBaseException(LittleRidersException e) {
        final LittleRidersErrorCode littleRidersErrorCode = e.getErrorCode();
        return ResponseEntity.status(littleRidersErrorCode.getStatus()).body(LittleRidersErrorResponse.from(littleRidersErrorCode.getCode()));
    }

    @ExceptionHandler
    protected ResponseEntity<?> handleMethodArgumentNotValidException( //todo : 익셉션 변경 필요
            MethodArgumentNotValidException e ) {
//        final ErrorResponse response = ErrorResponse.of(API_ERROR_INPUT_INVALID_VALUE, e.getBindingResult());
        log.warn(e.getMessage());
        Map<String,String> response = new HashMap<>();
        response.put("code","ERROR_1");
        response.put("error",e.getMessage());

        return ResponseEntity.status(500).body(response);
    }

}
