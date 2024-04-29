package kr.co.littleriders.backend.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {


    @NotBlank(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotBlank(message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;


    private SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static SignInRequest of(String email, String password) {
        return new SignInRequest(email, password);
    }
}
