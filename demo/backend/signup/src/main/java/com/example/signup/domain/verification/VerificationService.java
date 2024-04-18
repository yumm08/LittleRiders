package com.example.signup.domain.verification;


import com.example.signup.domain.verification.entity.Verification;
import com.example.signup.domain.verification.entity.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class VerificationService {


    private final VerificationRepository verificationRepository;
    public Verification findByEmailAndCodeAndType(String email, String code,VerificationType verificationType){
        Verification verification = verificationRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException());

        if( verification.isNotEqualsCode(code) || verification.isNotEqualsType(verificationType)){
            throw new RuntimeException();
        }

        return verification;
    }
    public boolean existsByEmailAndCodeAndType(String email,String code,VerificationType verificationType){
        try {
            this.findByEmailAndCodeAndType(email, code,verificationType);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void save(Verification verification){
        verificationRepository.save(verification);
    }


    @Async
    public Verification create(String email, VerificationType verificationType){
        Verification verification = Verification.of(email,verificationType);
        save(verification);
        return verification;
    }

    public Verification findByEmail(String email){
        return verificationRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException()
        );
    }

    public void delete(Verification verification){
        verificationRepository.delete(verification);
    }

}
