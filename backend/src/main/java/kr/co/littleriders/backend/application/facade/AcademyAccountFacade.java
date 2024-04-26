package kr.co.littleriders.backend.application.facade;

public interface AcademyAccountFacade {

    void sendSignUpEmail(String email);

    void sendChangePasswordEmail(String email);
}
