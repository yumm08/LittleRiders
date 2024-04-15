package com.example.signup.domain.registration;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class RegistrationCodeService {


    private final RegistrationCodeRepository registrationCodeRepository;


    public void save(RegistrationCode registrationCode) {
        registrationCodeRepository.save(registrationCode);
    }

    public RegistrationCode createRegistrationCode(String email) {

        RegistrationCode registrationCode = RegistrationCode.from(email);
        registrationCodeRepository.save(registrationCode);
        return registrationCode;
    }

    public RegistrationCode findByEmailAndCode(String email, String code) {
        RegistrationCode registrationCode = registrationCodeRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException()
        );
        if(registrationCode.isNotEqualsCode(code)){
            throw new RuntimeException();
        }
        return registrationCode;

    }

    public boolean existsByEmailAndCode(String email, String code) {
        try {
            findByEmailAndCode(email,code);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public void delete(RegistrationCode registrationCode) {
        registrationCodeRepository.delete(registrationCode);
    }
}
