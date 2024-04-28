package kr.co.littleriders.backend.application.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    private String email;
    private String password;


    private SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static SignInRequest of(String email, String password) {
        return new SignInRequest(email, password);
    }
}
