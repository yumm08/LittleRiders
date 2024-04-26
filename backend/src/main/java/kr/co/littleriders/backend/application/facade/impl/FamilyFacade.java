package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyException;
import kr.co.littleriders.backend.domain.token.RefreshTokenService;
import kr.co.littleriders.backend.domain.token.SignUpTokenService;
import kr.co.littleriders.backend.domain.token.entity.RefreshToken;
import kr.co.littleriders.backend.domain.token.entity.SignUpToken;
import kr.co.littleriders.backend.domain.token.entity.SignUpTokenType;
import kr.co.littleriders.backend.domain.verification.VerificationService;
import kr.co.littleriders.backend.domain.verification.entity.Verification;
import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.MemberErrorCode;
import kr.co.littleriders.backend.global.error.exception.MemberException;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import kr.co.littleriders.backend.global.mail.MailHelper;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FamilyFacade implements FamilyAccountFacade {

    private final FamilyService familyService;
    private final AcademyService academyService;

    private final VerificationService verificationService;

    private final RefreshTokenService refreshTokenService;

    private final SignUpTokenService signUpTokenService;

    private final MailHelper mailHelper;

    private final PasswordUtil passwordUtil;

    private final JwtProvider jwtProvider;

    @Override
    public String sendSignUpEmail(final String email) {
        if (familyService.existsByEmail(email) || academyService.existsByEmail(email)) {
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }
        Verification verification = Verification.of(email, VerificationType.FAMILY_SIGN_UP);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);

        return code;

    }

    @Override
    public void sendChangePasswordEmail(final String email) {
        if (familyService.notExistsByEmail(email)) {
            throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
        }
        Verification verification = Verification.of(email, VerificationType.FAMILY_CHANGE_PASSWORD);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);
    }

    @Override
    public String getSignUpToken(final String email, final String code) {
        Verification verification = verificationService.findFamilySignUpByEmailAndCode(email, code);
        verificationService.delete(verification);
        SignUpToken signUpToken = SignUpToken.of(email, SignUpTokenType.FAMILY);
        signUpTokenService.save(signUpToken);
        return signUpToken.getToken();
    }

    @Override
    @Transactional
    public void signUp(final FamilySignUpRequest familySignUpRequest,final String token) {
        String email = familySignUpRequest.getEmail();
        SignUpToken signUpToken = signUpTokenService.findFamilySignUpTokenByEmailAndToken(email, token);
        signUpTokenService.delete(signUpToken);
        Family family = familySignUpRequest.toFamily(passwordUtil);
        if(familyService.existsByEmail(email) || academyService.existsByEmail(email)){
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }
        familyService.save(family);
    }

    @Override
    public JwtToken signIn(final SignInRequest familySignInRequest) {
        String email = familySignInRequest.getEmail();
        String password = familySignInRequest.getPassword();

        Family family = familyService.findByEmail(email);
        if (passwordUtil.notEqualsPassword(password, family.getPassword())) {
            throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
        }
        JwtToken jwtToken = jwtProvider.createToken(family.getId(), MemberType.FAMILY);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);


        return jwtToken;
    }

//    @Override
//    public void signOut(String token) {
//        /*
//        TODO :
//               Refresh Token 이 VALID 한지 검사 -> 토큰 자체가 벨리드한지.
//               RefreshToken 은 redis 에서 delete
//         */
//
//        RefreshToken refreshToken = refreshTokenService.findByToken(token);
//        refreshTokenService.delete(refreshToken);
//
//    }
}
