package kr.co.littleriders.backend.application.facade;

public interface FamilyAccountFacade {

    void sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);
}
