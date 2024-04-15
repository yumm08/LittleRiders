package com.example.signup.application.facade;

import com.example.signup.application.dto.response.SignUpEmailValidResponse;
import com.example.signup.application.facade.SignUpFacade;
import com.example.signup.domain.member.MemberService;
import com.example.signup.domain.registration.RegistrationCode;
import com.example.signup.domain.registration.RegistrationCodeService;
import com.example.signup.domain.verification.VerificationService;
import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.entity.VerificationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class SignUpFacadeTest {


    @MockBean
    MemberService memberService;


    @MockBean
    RegistrationCodeService registrationCodeService;

    @MockBean
    VerificationService verificationService;

    @Autowired
    SignUpFacade signUpFacade;

    @Nested
    @DisplayName("이메일 발송 테스트")
    class sendEmailTest {

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            given(memberService.existsByEmail(any())).willReturn(false);
            signUpFacade.sendVerificationCode("test@apple.com");
        }

        @Test
        @DisplayName("실패, 이메일을 이미 사용중인 경우")
        void whenFailEmailAlreadyUse() {
            given(memberService.existsByEmail(any())).willReturn(true);

            assertThatThrownBy(() -> signUpFacade.sendVerificationCode("test@ruu.kr"))
                    .isInstanceOf(RuntimeException.class);
        }

    }


    @Nested
    @DisplayName("인증 완료 테스트")
    class getRegistrationCode {
        @Test
        @DisplayName("성공")
        void whenSuccess() {

            //given
            Verification verification = Verification.of("apple@test.com", VerificationType.SIGN_UP);
            RegistrationCode registrationCode = RegistrationCode.from("apple@test.com");
            given(verificationService.existsByEmailAndCodeAndType(any(), any(), any())).willReturn(true);
            given(verificationService.findByEmailAndCodeAndType(any(), any(), any())).willReturn(verification);
            given(registrationCodeService.createRegistrationCode(any())).willReturn(registrationCode);

            //when
            SignUpEmailValidResponse signUpEmailValidResponse = signUpFacade.getRegistrationCode("apple@test.com", "1234");


            //then
            assertThat(signUpEmailValidResponse.getCode()).matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");

        }

        @Test
        @DisplayName("실패, 인증 코드를 못 찾은 경우")
        void whenFailVerificationCodeNotFound() {

            given(verificationService.existsByEmailAndCodeAndType(any(), any(), any())).willReturn(false);

            assertThrows(RuntimeException.class, () -> {
                        signUpFacade.getRegistrationCode("apple@test.com", "1234");
                    }
            );

        }
    }
}