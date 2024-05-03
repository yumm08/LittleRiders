package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.common.fixture.FamilyFixture;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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



            FamilyFixture familyFixture  = FamilyFixture.KIM;
            String email = familyFixture.getEmail();
            String password = familyFixture.getPassword();
            String mailReceived = familyAccountFacade.sendSignUpEmail(email);

            String uuid = familyAccountFacade.getSignUpToken(email, mailReceived);
            FamilySignUpRequest familySignUpRequest = familyFixture.tofamilySignUpRequest();
            familyAccountFacade.signUp(familySignUpRequest,uuid);

            SignInRequest signInRequest = new SignInRequest(email,password);
            JwtToken jwtToken = familyAccountFacade.signIn(signInRequest);
            String token = jwtToken.getRefreshToken();
            JwtToken reIssuedToken = accountFacade.tokenReIssue(token);
            log.info("accessToken=[{}]",reIssuedToken.getAccessToken());
            log.info("refreshToken=[{}]",reIssuedToken.getRefreshToken());
        }
    }
}