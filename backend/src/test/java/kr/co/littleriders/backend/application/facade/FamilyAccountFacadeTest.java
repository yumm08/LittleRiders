package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.family.FamilyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class FamilyAccountFacadeTest {

    @Autowired
    FamilyAccountFacade familyAccountFacade;

    @SpyBean

    FamilyService familyService;

    @SpyBean
    AcademyService academyService;


    @Nested
    @DisplayName("sendSignUpEmail 테스트")
    class sendSignUpEmail {
        @Test
        @DisplayName("성공")
        void whenSuccess() {
            familyAccountFacade.sendSignUpEmail("test@ruu.kr");
        }

        @Test
        @DisplayName("실패, Family가 email 을 사용중인 경우")
        void whenFailFamilyUseEmail() {
            //given
            given(familyService.existsByEmail(any())).willReturn(true);

            //when

            assertThrows(RuntimeException.class, () -> {
                familyAccountFacade.sendSignUpEmail("test@ruu.kr");
            });

        }

        @Test
        @DisplayName("실패, Academy가 email 을 사용중인 경우")
        void whenFailAcademyUseEmail() {
            //given
            given(academyService.existsByEmail(any())).willReturn(true);

            //when
            assertThrows(RuntimeException.class, () -> {
                familyAccountFacade.sendSignUpEmail("test@ruu.kr");
            });

        }

    }

}