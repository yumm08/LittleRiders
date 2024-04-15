package com.example.signup.domain.verification;

import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.entity.VerificationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VerificationServiceTest {

    @Autowired
    VerificationService verificationService;



    @Nested
    @DisplayName("create test")
    class create{

        @Test
        @DisplayName("성공")
        void whenSuccess(){
            Verification verification = verificationService.create("test@ruu.kr", VerificationType.SIGN_UP);


        }
    }

}