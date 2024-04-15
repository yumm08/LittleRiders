package com.example.signup.application.dto.response;


import com.example.signup.domain.registration.RegistrationCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class SignUpEmailValidResponse {

    private final String code;


    public static SignUpEmailValidResponse of(RegistrationCode registrationCode){
        return new SignUpEmailValidResponse(registrationCode.getCode());
    }
}
