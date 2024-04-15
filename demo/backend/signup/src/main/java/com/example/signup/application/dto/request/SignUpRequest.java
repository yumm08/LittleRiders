package com.example.signup.application.dto.request;

import com.example.signup.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String code;

    public Member toEntity() {
        return Member.of(email,name,password);
    }
}
