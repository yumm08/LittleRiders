package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.facade.AccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyException;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.ShuttleTerminalAttachException;
import kr.co.littleriders.backend.domain.token.RefreshTokenService;
import kr.co.littleriders.backend.domain.token.entity.RefreshToken;
import kr.co.littleriders.backend.domain.verification.VerificationService;
import kr.co.littleriders.backend.domain.verification.entity.Verification;
import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
import kr.co.littleriders.backend.domain.verification.error.code.VerificationErrorCode;
import kr.co.littleriders.backend.domain.verification.error.exception.VerificationException;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import kr.co.littleriders.backend.global.jwt.JwtMemberInfo;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import kr.co.littleriders.backend.global.mail.MailHelper;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class AccountFacadeImpl implements AccountFacade {

    private final RefreshTokenService refreshTokenService;

    private final JwtProvider jwtProvider;

    private final FamilyService familyService;

    private final AcademyService academyService;

    private final PasswordUtil passwordUtil;

    private final TerminalService terminalService;

    private final VerificationService verificationService;

    private final MailHelper mailHelper;

    @Override
    public JwtToken tokenReIssue(final String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token);
        refreshTokenService.delete(refreshToken);
        JwtMemberInfo jwtMemberInfo = jwtProvider.getJwtMemberInfoByRefreshToken(token);

        Long id = jwtMemberInfo.getMemberId();
        MemberType memberType = jwtMemberInfo.getMemberType();

        if (memberType == MemberType.FAMILY) { //신원 검증
            if (familyService.notExistsById(id)) {
                throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
            }
        }

        if (memberType == MemberType.ACADEMY) {
            if (academyService.notExistsById(id)) { //신원 검증
                throw AcademyException.from(AcademyErrorCode.NOT_FOUND);
            }
        }
        if (memberType == MemberType.TERMINAL) {
            Terminal terminal = terminalService.findById(id);
            if (terminal.getShuttleTerminalAttach() == null) {
                throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
            }
        }

        JwtToken jwtToken = jwtProvider.createToken(id, memberType);
        refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    public void signOut(String requestRefreshToken) {
        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);
        refreshTokenService.delete(refreshToken);
    }

    @Override
    public JwtToken signIn(String email, String password) { //리팩토링이 될거같음
        if (familyService.existsByEmail(email)) {
            Family family = familyService.findByEmail(email);
            if (passwordUtil.notEqualsPassword(password, family.getPassword())) {
                throw AuthException.from(AuthErrorCode.PASSWORD_NOT_EQUAL);
            }
            JwtToken jwtToken = jwtProvider.createToken(family.getId(), MemberType.FAMILY);
            RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
            refreshTokenService.save(refreshToken);
            return jwtToken;
        }
        if (academyService.existsByEmail(email)) {
            Academy academy = academyService.findByEmail(email);
            if (passwordUtil.notEqualsPassword(password, academy.getPassword())) {
                throw AuthException.from(AuthErrorCode.PASSWORD_NOT_EQUAL);
            }
            JwtToken jwtToken = jwtProvider.createToken(academy.getId(), MemberType.ACADEMY);
            RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
            refreshTokenService.save(refreshToken);
            return jwtToken;
        }
        throw AuthException.from(AuthErrorCode.USER_NOT_FOUND);

    }

    @Override
    public JwtToken signInByTerminalNumber(String terminalNumber) {
        Terminal terminal = terminalService.findByTerminalNumber(terminalNumber);
        if (terminal.getShuttleTerminalAttach() == null) {
            throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
        }
        JwtToken jwtToken = jwtProvider.createToken(terminal.getId(), MemberType.TERMINAL);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    public void sendChangePasswordEmail(String email) {
        if (familyService.existsByEmail(email) && academyService.notExistsByEmail(email)) {
            throw AuthException.from(AuthErrorCode.USER_NOT_FOUND);
        }
        VerificationType verificationType = familyService.existsByEmail(email) ? VerificationType.FAMILY_CHANGE_PASSWORD : VerificationType.ACADEMY_CHANGE_PASSWORD;
        Verification verification = Verification.of(email, verificationType);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendChangePasswordVerificationEmail(email, code);

    }

    @Override
    public JwtToken signInByEmailAndVerificationCode(String email, String code) {
        Verification verification = verificationService.findByEmail(email);
        VerificationType verificationType = verification.getType();
        if (verificationType != VerificationType.ACADEMY_CHANGE_PASSWORD && verificationType != VerificationType.FAMILY_CHANGE_PASSWORD) {
            throw VerificationException.from(VerificationErrorCode.NOT_FOUND);
        }
        MemberType memberType = verificationType == VerificationType.ACADEMY_CHANGE_PASSWORD ? MemberType.ACADEMY : MemberType.FAMILY;
        long id = 0;
        if (memberType == MemberType.ACADEMY) {
            id = academyService.findByEmail(email).getId();
        }
        id = familyService.findByEmail(email).getId();
        JwtToken jwtToken = jwtProvider.createToken(id, memberType);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        verificationService.delete(verification);
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    public void changePassword(AuthDTO authDTO, String password) {
        log.info("changePasswordCalled");
        String encrypted = passwordUtil.encrypt(password);
        if (authDTO instanceof AuthFamily authFamily) {
            Family family = familyService.findById(authFamily.getId());
            family.setPassword(encrypted);
            familyService.save(family);
            return;
        }
        if(authDTO instanceof AuthAcademy authAcademy){
            Academy academy = academyService.findById(authAcademy.getId());
            academy.setPassword(encrypted);
            academyService.save(academy);
            return;
        }
        throw AuthException.from(AuthErrorCode.NOT_VALID_REQUEST);
    }


}
