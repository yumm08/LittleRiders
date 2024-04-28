package kr.co.littleriders.backend.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpValidateEmailRequest {

    private String email;
    private String code;


}
