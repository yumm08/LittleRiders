package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AccountFacadeTest {

    @Autowired
    AccountFacade accountFacade;


    @Autowired
    FamilyAccountFacade familyAccountFacade;

    @Nested
    @DisplayName("tokenReIssue 테스트")
    class tokenReIssue{

        @Test
        @DisplayName("성공")
        void whenSuccess(){


            String email = "test@example.com";
            String password = "123456";
            String mailReceived = familyAccountFacade.sendSignUpEmail(email);

            String uuid = familyAccountFacade.validateEmailWithCode(email, mailReceived).getToken();
            FamilySignUpRequest familySignUpRequest = new FamilySignUpRequest(
                    email,
                    password,
                    "테스트",
                    "01012345678",
                    uuid
            );
            familyAccountFacade.signUp(familySignUpRequest);

            SignInRequest signInRequest = SignInRequest.of(email,password);
            JwtToken jwtToken = familyAccountFacade.signIn(signInRequest);

            String token = jwtToken.getRefreshToken();
            JwtToken reIssuedToken = accountFacade.tokenReIssue(token);

            log.info("accessToken=[{}]",reIssuedToken.getAccessToken());
            log.info("refreshToken=[{}]",reIssuedToken.getRefreshToken());
        }
    }
}