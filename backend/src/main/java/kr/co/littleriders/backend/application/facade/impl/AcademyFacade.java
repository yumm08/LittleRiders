package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.facade.AcademyAccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyException;
import kr.co.littleriders.backend.domain.verification.VerificationService;
import kr.co.littleriders.backend.domain.verification.entity.Verification;
import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
import kr.co.littleriders.backend.global.mail.MailHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AcademyFacade implements AcademyAccountFacade {

    private final FamilyService familyService;
    private final AcademyService academyService;

    private final VerificationService verificationService;

    private final MailHelper mailHelper;

    @Override
    public void sendSignUpEmail(final String email) {
        if (familyService.existsByEmail(email) || academyService.existsByEmail(email)) {
            throw new RuntimeException();
        }
        Verification verification = Verification.of(email, VerificationType.ACADEMY_SIGN_UP);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);

    }

    @Override
    public void sendChangePasswordEmail(final String email) {
        if (academyService.notExistsByEmail(email)) {
            throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
        }
        Verification verification = Verification.of(email, VerificationType.ACADEMY_CHANGE_PASSWORD);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);
    }
}
