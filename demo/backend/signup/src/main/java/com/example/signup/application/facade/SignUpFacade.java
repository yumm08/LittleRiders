package com.example.signup.application.facade;


import com.example.signup.application.dto.request.SignUpRequest;
import com.example.signup.application.dto.response.SignUpEmailValidResponse;
import com.example.signup.domain.mail.MailService;
import com.example.signup.domain.member.Member;
import com.example.signup.domain.member.MemberService;
import com.example.signup.domain.registration.RegistrationCode;
import com.example.signup.domain.registration.RegistrationCodeService;
import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.VerificationService;
import com.example.signup.domain.verification.entity.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SignUpFacade {

    private final VerificationService verificationService;
    private final MemberService memberService;

    private final MailService mailService;

    private final RegistrationCodeService registrationCodeService;

    public void sendVerificationCode(String email) { //책임을 어디다 둬야 할까?
        if (memberService.existsByEmail(email)) {
            throw new RuntimeException();
        }
        //어차피 여기 레벨에서 막아서 ㄱㅊ을듯
        CompletableFuture<Verification> verificationFuture = CompletableFuture.supplyAsync(//책임의 대상이 이상함
                () -> verificationService.create(email, VerificationType.SIGN_UP)
        );
        verificationFuture.thenAcceptAsync(v -> mailService.sendEmail(v.getEmail(), "회원가입 인증 코드 입니다", v.getCode()));
    }

    public SignUpEmailValidResponse getRegistrationCode(String email, String code) { //it should be return uuid at client
        if (!verificationService.existsByEmailAndCodeAndType(email, code,VerificationType.SIGN_UP)) {
            throw new RuntimeException(); //code NotFound Error Handle at facade
        }
        Verification verification = verificationService.findByEmailAndCodeAndType(email, code,VerificationType.SIGN_UP);
        verificationService.delete(verification);
        //email save and generate something;
        //if Exists then Throw error

        //if email in registrationCode then throw error;
        RegistrationCode registrationCode = registrationCodeService.createRegistrationCode(email);
        return SignUpEmailValidResponse.of(registrationCode);

    }

    public void signUp(SignUpRequest signUpRequest){
        String email = signUpRequest.getEmail();
        String code = signUpRequest.getCode();
        if(!registrationCodeService.existsByEmailAndCode(email,code)){
            throw new RuntimeException();
        }
        if(memberService.existsByEmail(email)){
            throw new RuntimeException() ;
        }
        RegistrationCode registrationCode = registrationCodeService.findByEmailAndCode(email,code);
        registrationCodeService.delete(registrationCode);

        Member member = signUpRequest.toEntity();
        memberService.save(member);


    }

}
