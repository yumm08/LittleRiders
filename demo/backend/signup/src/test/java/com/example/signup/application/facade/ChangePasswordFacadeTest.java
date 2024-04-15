package com.example.signup.application.facade;

import com.example.signup.domain.mail.MailService;
import com.example.signup.domain.member.Member;
import com.example.signup.domain.member.MemberService;
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

import java.util.concurrent.CompletableFuture;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class ChangePasswordFacadeTest {
    @MockBean
    MemberService memberService;
    @MockBean
    VerificationService verificationService;
    @MockBean
    MailService mailService;

    @Autowired
    ChangePasswordFacade changePasswordFacade;

    @Nested
    @DisplayName("이메일 발송 테스트")
    class sendVerificationCode{

        @Test
        @DisplayName("성공")
        void whenSuccess(){

            Verification verification = Verification.of("test@ruu.kr",VerificationType.CHANGE_PASSWORD);
            //given
            given(memberService.existsByEmail(any())).willReturn(true);
            given(verificationService.create(any(),any())).willReturn(verification);
            doNothing().when(mailService).sendEmail(any(),any(),any());

            assertDoesNotThrow(() ->{
                changePasswordFacade.sendVerificationCode("test@ruu.kr");
            });

        }
    }

}




//    public long verificationCode(String email, String code){
//        if (!verificationService.existsByEmailAndCodeAndType(email,code,VerificationType.CHANGE_PASSWORD)){ //if Not exists then throw Exception
//            throw  new RuntimeException();
//        }
//        Verification verification = verificationService.findByEmailAndCodeAndType(email,code,VerificationType.CHANGE_PASSWORD);
//        verificationService.delete(verification);
//        Member member = memberService.findByEmail(email);
//        long id = member.getId();
//
//        return id;  //it should be return access_token and refresh_token;
//    }
//
//
//    public void changePassword(String email, String password){ //input format should be jwt, NOT EMAIL IN PRODUCTION CODE
//        Member member = memberService.findByEmail(email); // it should be return member;
//        member.setPassword(password);
//        memberService.save(member);
//    }