package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface AcademyAccountFacade {

    String sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);

    void signUp(AcademySignUpRequest academySignUpRequest, String token);

    JwtToken signIn(SignInRequest academySignInRequest);

    String getSignUpToken(String email, String code);
}
