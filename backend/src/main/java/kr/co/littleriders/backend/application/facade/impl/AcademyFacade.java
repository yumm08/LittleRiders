package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.client.AddressConvertFetchAPI;
import kr.co.littleriders.backend.application.client.AddressConvertPositionClientRequest;
import kr.co.littleriders.backend.application.client.AddressConvertPositionClientResponse;
import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.facade.AcademyAccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import kr.co.littleriders.backend.domain.family.FamilyService;
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
class AcademyFacade implements AcademyAccountFacade {

    private final FamilyService familyService;
    private final AcademyService academyService;

    private final VerificationService verificationService;
    private final RefreshTokenService refreshTokenService;

    private final SignUpTokenService signUpTokenService;

    private final MailHelper mailHelper;

    private final PasswordUtil passwordUtil;

    private final JwtProvider jwtProvider;

    private final AddressConvertFetchAPI addressConvertFetchAPI;

    @Override
    public String sendSignUpEmail(final String email) {
        if (familyService.existsByEmail(email) || academyService.existsByEmail(email)) {
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }
        Verification verification = Verification.of(email, VerificationType.ACADEMY_SIGN_UP);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);
        return code;
    }

    @Override
    public void sendChangePasswordEmail(final String email) {
        if (academyService.notExistsByEmail(email)) {
            throw AcademyException.from(AcademyErrorCode.NOT_FOUND);
        }
        Verification verification = Verification.of(email, VerificationType.ACADEMY_CHANGE_PASSWORD);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendVerificationEmail(email, code);
    }

    @Override
    public String getSignUpToken(String email, String code){
        Verification verification = verificationService.findAcademySignUpByEmailAndCode(email, code);
        verificationService.delete(verification);
        SignUpToken signUpToken = SignUpToken.of(email, SignUpTokenType.ACADEMY);
        signUpTokenService.save(signUpToken);
        return signUpToken.getToken();
    }

    @Override
    @Transactional
    public void signUp(final AcademySignUpRequest academySignUpRequest, final String token) {

        String email = academySignUpRequest.getEmail();
        SignUpToken signUpToken = signUpTokenService.findFamilySignUpTokenByEmailAndToken(email, token);
        signUpTokenService.delete(signUpToken);
        if (familyService.existsByEmail(email) || academyService.existsByEmail(email)) {
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }

        String address = academySignUpRequest.getAddress();
        AddressConvertPositionClientRequest addressConvertPositionClientRequest = AddressConvertPositionClientRequest.from(address);

        AddressConvertPositionClientResponse addressConvertPositionClientResponse = addressConvertFetchAPI.fetchAPI(addressConvertPositionClientRequest);

        double latitude = addressConvertPositionClientResponse.getLatitude();
        double longitude = addressConvertPositionClientResponse.getLongitude();


        Academy academy = academySignUpRequest.toAcademy(passwordUtil,latitude,longitude);
        academyService.save(academy);
    }

    @Override
    public JwtToken signIn(final SignInRequest academySignInRequest) {
        String email = academySignInRequest.getEmail();
        String password = academySignInRequest.getPassword();

        Academy academy = academyService.findByEmail(email);
        if (passwordUtil.notEqualsPassword(password, academy.getPassword())) {
            throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
        }
        JwtToken jwtToken = jwtProvider.createToken(academy.getId(), MemberType.ACADEMY);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);


        return jwtToken;
    }
}
