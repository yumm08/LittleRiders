package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface AccountFacade {

    JwtToken tokenReIssue(String token);

    void signOut(String requestRefreshToken);

    JwtToken signIn(String email,String password);

    JwtToken signInByTerminalNumber(String terminalNumber);
}
