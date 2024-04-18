package com.example.signup;

import com.example.signup.application.dto.request.SignUpRequest;
import com.example.signup.application.dto.response.SignUpEmailValidResponse;
import com.example.signup.application.facade.SignUpFacade;
import com.example.signup.domain.mail.MailService;
import com.example.signup.domain.registration.RegistrationCode;
import com.example.signup.domain.verification.VerificationService;
import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.entity.VerificationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class SignupApplicationTests {

    @Autowired
    SignUpFacade signUpFacade;

    @Autowired
    VerificationService verificationService;

    @Autowired
    MailService mailService;


    @Nested
    @DisplayName("회원가입에 대한 통합 테스트")
    class signUp {
        @Test
        @DisplayName("성공, 회원가입에 대한 모든 작업이 완료됨")
        void whenSuccess() {
            //다음과 같은 작업을 수행할것임
            //1. 이메일 발송
            //2. 이메일 검증
            //3. 회원 가입


            String email = "test@ruu.kr";

            signUpFacade.sendVerificationCode(email); //메일 발송
            Verification verification;

            do { //이상한 테스트긴한듯
                try {
                    verification = verificationService.findByEmail(email);
                    break;
                } catch (RuntimeException e) {

                }
            } while (true);

            SignUpEmailValidResponse signUpEmailValidResponse = signUpFacade.getRegistrationCode(email, verification.getCode()); //이메일 검증
            SignUpRequest signUpRequest = new SignUpRequest("햄버거", email, "myPassword", signUpEmailValidResponse.getCode());
            signUpFacade.signUp(signUpRequest);

            //이메일 검증
            //회원가입
        }
    }

}
