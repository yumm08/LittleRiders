package kr.co.littleriders.backend.application.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.dto.request.SignUpValidateEmailRequest;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/family/account")
@RequiredArgsConstructor
@Slf4j
public class FamilyAccountController {
    private final FamilyAccountFacade familyAccountFacade;

    @GetMapping("/sign-up/validate")
    public ResponseEntity<Void> sendSignUpVerificationMail(@RequestParam String email) {
        familyAccountFacade.sendSignUpEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up/validate")
    public ResponseEntity<?> validateEmailWithCode(@RequestBody SignUpValidateEmailRequest signUpValidateEmailRequest, HttpServletResponse response) {
        log.info("validateEmailWithCode: call");
        String email = signUpValidateEmailRequest.getEmail();
        String code = signUpValidateEmailRequest.getCode();
        String signUpToken = familyAccountFacade.getSignUpToken(email, code);
        Cookie cookie = new Cookie("signup-token", signUpToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody FamilySignUpRequest familySignUpRequest, @CookieValue("signup-token") String token) {
        log.info("signup-token = [{}]",token);
        familyAccountFacade.signUp(familySignUpRequest,token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest, HttpServletResponse response) {
        JwtToken jwtToken = familyAccountFacade.signIn(signInRequest);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setMaxAge(jwtToken.getRefreshTokenExpTimeToSecond());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().headers(headers).build();
    }


    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Auth AuthFamily authFamily) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("AuthFamily = {}",objectMapper.writeValueAsString(authFamily));
        return ResponseEntity.ok(objectMapper.writeValueAsString(authFamily));
    }
}
