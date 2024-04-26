package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.facade.AccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyException;
import kr.co.littleriders.backend.domain.token.RefreshTokenService;
import kr.co.littleriders.backend.domain.token.entity.RefreshToken;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.jwt.JwtMemberInfo;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AccountFacadeImpl implements AccountFacade {

    private final RefreshTokenService refreshTokenService;

    private final JwtProvider jwtProvider;

    private final FamilyService familyService;

    private final AcademyService academyService;

    @Override
    public JwtToken tokenReIssue(final String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token);
        refreshTokenService.delete(refreshToken);
        JwtMemberInfo jwtMemberInfo = jwtProvider.getJwtMemberInfoByRefreshToken(token);

        Long id = jwtMemberInfo.getMemberId();
        MemberType memberType = jwtMemberInfo.getMemberType();

        if(memberType == MemberType.FAMILY){ //신원 검증
            if(familyService.notExistsById(id)){
                throw FamilyException.from(FamilyErrorCode.NOT_FOUND);
            }
        }
        else{
            if(academyService.notExistsById(id)){ //신원 검증
                throw AcademyException.from(AcademyErrorCode.NOT_FOUND);
            }
        }

        JwtToken jwtToken =  jwtProvider.createToken(id,memberType);
        refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }
}
