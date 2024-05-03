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

class FamilyAccountFacadeTest {

    @Autowired
    FamilyAccountFacade familyAccountFacade;

//
//    @Nested
//    @DisplayName("sendSignUpEmail 테스트")
//    class sendSignUpEmail {
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() {
//            assertDoesNotThrow(
//                    () -> {
//                        familyAccountFacade.sendSignUpEmail("test@ruu.kr");
//                    }
//            );
//        }
//
//        @Test
//        @DisplayName("실패, Family가 email 을 사용중인 경우")
//        void whenFailFamilyUseEmail() {
//            //given
//            given(familyService.existsByEmail(any())).willReturn(true);
//
//            //when
//            assertThrows(RuntimeException.class, () -> {
//                familyAccountFacade.sendSignUpEmail("test@ruu.kr");
//            });
//
//        }
//
//        @Test
//        @DisplayName("실패, Academy가 email 을 사용중인 경우")
//        void whenFailAcademyUseEmail() {
//            //given
//            given(academyService.existsByEmail(any())).willReturn(true);
//
//            //when
//            assertThrows(RuntimeException.class, () -> {
//                familyAccountFacade.sendSignUpEmail("test@ruu.kr");
//            });
//
//        }
//
//    }


    @Nested
    @DisplayName("회원가입 테스트")
    class registerTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() throws InterruptedException {

            String email = "test@example2.com";
            String mailReceived = familyAccountFacade.sendSignUpEmail(email);

            String uuid = familyAccountFacade.getSignUpToken(email, mailReceived);
            FamilySignUpRequest familySignUpRequest = new FamilySignUpRequest(
                    email,
                    "123456",
                    "테스트",
                    "집주소",
                    "01012345678"
            );
            familyAccountFacade.signUp(familySignUpRequest,uuid);
        }
    }


    @Nested
    @DisplayName("로그인 테스트")
    class signInTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() throws InterruptedException {

            FamilyFixture familyFixture = FamilyFixture.KIM;
            String email = familyFixture.getEmail();
            String password = familyFixture.getPassword();
            String mailReceived = familyAccountFacade.sendSignUpEmail(email);

            String uuid = familyAccountFacade.getSignUpToken(email, mailReceived);
            FamilySignUpRequest familySignUpRequest = familyFixture.tofamilySignUpRequest();
            familyAccountFacade.signUp(familySignUpRequest,uuid);

            SignInRequest signInRequest = new SignInRequest(email,password);
            JwtToken jwtToken = familyAccountFacade.signIn(signInRequest);

            log.info("AccessToken = [{}]",jwtToken.getAccessToken());
            log.info("RefreshToken = [{}]",jwtToken.getRefreshToken());



        }
    }

}