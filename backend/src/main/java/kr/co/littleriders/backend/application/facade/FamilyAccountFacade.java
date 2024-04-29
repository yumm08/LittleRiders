package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface FamilyAccountFacade {

    String sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);

    void signUp(FamilySignUpRequest familySignUpRequest,String token);

    JwtToken signIn(SignInRequest familySignInRequest);

    String getSignUpToken(String email, String code);
}
