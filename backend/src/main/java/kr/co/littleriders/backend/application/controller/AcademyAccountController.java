package kr.co.littleriders.backend.application.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.dto.request.SignUpValidateEmailRequest;
import kr.co.littleriders.backend.application.facade.AcademyAccountFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/academy/account")
@RequiredArgsConstructor
@Slf4j
public class AcademyAccountController {
    private final AcademyAccountFacade academyAccountFacade;

    @GetMapping("/sign-up/validate")
    public ResponseEntity<Void> sendSignUpVerificationMail(@RequestParam String email) {
        academyAccountFacade.sendSignUpEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up/validate")
    public ResponseEntity<Void> validateEmailWithCode(@Valid  @RequestBody SignUpValidateEmailRequest signUpValidateEmailRequest, HttpServletResponse response) {

        String email = signUpValidateEmailRequest.getEmail();
        String code = signUpValidateEmailRequest.getCode();
        String signUpToken = academyAccountFacade.getSignUpToken(email, code);
        Cookie cookie = new Cookie("signup-token", signUpToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody AcademySignUpRequest academySignUpRequest, @CookieValue("signup-token") String token) {
        academyAccountFacade.signUp(academySignUpRequest,token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest, HttpServletResponse response) {
        JwtToken jwtToken = academyAccountFacade.signIn(signInRequest);
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


    @Deprecated
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Auth AuthAcademy authAcademy) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("AuthAcademy = {}",objectMapper.writeValueAsString(authAcademy));
        return ResponseEntity.ok(objectMapper.writeValueAsString(authAcademy));
    }
}
