package kr.co.littleriders.backend.domain.email;

import kr.co.littleriders.backend.domain.email.entity.FamilySignUpEmailVerification;

public interface FamilySignUpEmailVerificationService {

    FamilySignUpEmailVerification findByEmailAndCode(String email, String code);

    void save(FamilySignUpEmailVerification familySignUpEmailVerification);
}
