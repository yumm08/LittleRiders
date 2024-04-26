package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.token.entity.SignUpToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ValidateEmailResponse {

    private final String token;

    public static ValidateEmailResponse from(SignUpToken signUpToken) {
        return new ValidateEmailResponse(signUpToken.getToken());
    }
}
