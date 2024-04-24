package kr.co.littleriders.backend.domain.verification;

import kr.co.littleriders.backend.domain.verification.entity.Verification;

public interface VerificationService {
    Verification findFamilySignUpByEmailAndCode(String email, String code);

    void save(Verification verification);

    void extendsTimeByEmail(String email);
}
