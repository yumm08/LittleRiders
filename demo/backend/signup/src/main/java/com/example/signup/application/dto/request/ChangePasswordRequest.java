package com.example.signup.application.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {

    private String email;
    private String password;

}
