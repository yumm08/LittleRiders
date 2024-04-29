package kr.co.littleriders.backend.global.auth;


import jakarta.servlet.http.HttpServletRequest;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import kr.co.littleriders.backend.global.jwt.JwtMemberInfo;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final FamilyService familyService;
    private final AcademyService academyService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameter().getType();
        return parameter.hasParameterAnnotation(Auth.class) && AuthDTO.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        Class<?> parameterType = parameter.getParameterType();

        log.info("resolving argument before casting = {}",parameterType);

        MemberType parameterMemberType = parameterType.equals(AuthFamily.class) ? MemberType.FAMILY : MemberType.ACADEMY;


        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest == null) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_FOUND);
        }

        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_FOUND);
        }
        if (!token.startsWith("Bearer ")) {
            throw AuthException.from(AuthErrorCode.AUTHORIZATION_NOT_VALID);
        }
        token = token.substring(7);
        JwtMemberInfo jwtMemberInfo = jwtProvider.getJwtMemberInfoByAccessToken(token);

        long memberId = jwtMemberInfo.getMemberId();
        MemberType memberType = jwtMemberInfo.getMemberType();
        if(parameterMemberType != memberType){
            throw AuthException.from(AuthErrorCode.JWT_NOT_SUPPORT);
        }
        if(memberType == MemberType.ACADEMY){
            Academy academy = academyService.findById(memberId);
            return AuthAcademy.from(academy);
        }
        Family family = familyService.findById(memberId);
        return AuthFamily.from(family);

    }
}
