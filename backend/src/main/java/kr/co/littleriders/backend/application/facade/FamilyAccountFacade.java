package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.response.ValidateEmailResponse;

public interface FamilyAccountFacade {

    String sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);

    ValidateEmailResponse validateEmailWithCode(String email, String code);

    void signUp(FamilySignUpRequest familySignUpRequest);
}
