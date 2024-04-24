package kr.co.littleriders.backend.domain.email.service;

import kr.co.littleriders.backend.domain.email.FamilySignUpEmailVerificationService;
import kr.co.littleriders.backend.domain.email.entity.FamilySignUpEmailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FamilySignUpEmailVerificationServiceImpl implements FamilySignUpEmailVerificationService {

    private final FamilySignUpEmailVerificationRepository familySignUpEmailVerificationRepository;

    @Override
    public FamilySignUpEmailVerification findByEmailAndCode(final String email, final String code) {
        FamilySignUpEmailVerification familySignUpEmailVerification = familySignUpEmailVerificationRepository.findByEmail(email).orElseThrow(
                RuntimeException::new
        );
        if (familySignUpEmailVerification.notEqualsCode(email)) {

            throw new RuntimeException();
        }
        return familySignUpEmailVerification;
    }

    @Override
    public void save(FamilySignUpEmailVerification familySignUpEmailVerification) {
        familySignUpEmailVerificationRepository.save(familySignUpEmailVerification);
    }
}
