package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface AccountFacade {

    JwtToken tokenReIssue(String token);

    void signOut(String requestRefreshToken);

    JwtToken signIn(String email,String password);

    JwtToken signInByTerminalNumber(String terminalNumber);

    void sendChangePasswordEmail(String email);

    JwtToken signInByEmailAndVerificationCode(String email, String code);

    void changePassword(AuthFamily authFamily, String password);

    void changePassword(AuthAcademy authAcademy, String password);
}
