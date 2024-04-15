package com.example.signup.application.facade;


import com.example.signup.domain.mail.MailService;
import com.example.signup.domain.member.Member;
import com.example.signup.domain.member.MemberService;
import com.example.signup.domain.registration.RegistrationCode;
import com.example.signup.domain.registration.RegistrationCodeService;
import com.example.signup.domain.verification.VerificationService;
import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.entity.VerificationType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePasswordFacade {

    private final MemberService memberService;
    private final VerificationService verificationService;
    private final MailService mailService;

    public void sendVerificationCode(String email) {
        if (!memberService.existsByEmail(email)) {
            throw new RuntimeException();
        }
        CompletableFuture<Verification> verificationFuture = CompletableFuture.supplyAsync(//책임의 대상이 이상함
                () -> verificationService.create(email, VerificationType.CHANGE_PASSWORD)
        );
        verificationFuture.thenAcceptAsync(v -> mailService.sendEmail(v.getEmail(), "로그인 인증 코드 입니다", v.getCode()));
    }

    public long verificationCode(String email, String code){
        if (!verificationService.existsByEmailAndCodeAndType(email,code,VerificationType.CHANGE_PASSWORD)){ //if Not exists then throw Exception
            throw  new RuntimeException();
        }
        Verification verification = verificationService.findByEmailAndCodeAndType(email,code,VerificationType.CHANGE_PASSWORD);
        verificationService.delete(verification);
        Member member = memberService.findByEmail(email);
        long id = member.getId();

        return id;  //it should be return access_token and refresh_token;
    }


    public void changePassword(String email, String password){ //input format should be jwt, NOT EMAIL IN PRODUCTION CODE
        Member member = memberService.findByEmail(email); // it should be return member;
        member.setPassword(password);
        memberService.save(member);
    }




}
