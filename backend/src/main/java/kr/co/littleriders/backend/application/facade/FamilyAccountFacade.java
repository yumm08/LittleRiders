package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.dto.response.ValidateEmailResponse;
import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface FamilyAccountFacade {

    String sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);

    ValidateEmailResponse validateEmailWithCode(String email, String code);

    void signUp(FamilySignUpRequest familySignUpRequest);

    JwtToken signIn(SignInRequest familySignInRequest);
}
