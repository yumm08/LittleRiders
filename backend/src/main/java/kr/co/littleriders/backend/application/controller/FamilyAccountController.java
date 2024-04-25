package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.dto.response.ValidateEmailResponse;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/family/account")
@RequiredArgsConstructor
public class FamilyAccountController {
    private final FamilyAccountFacade familyAccountFacade;

    @GetMapping("/validate")
    public ResponseEntity<Void> sendSignUpVerificationMail(@RequestParam String email) {
        familyAccountFacade.sendSignUpEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateEmailResponse> validateEmailWithCode(@RequestBody String email, @RequestBody String code) {
        ValidateEmailResponse validateEmailResponse = familyAccountFacade.validateEmailWithCode(email, code);
        return ResponseEntity.ok().body(validateEmailResponse);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody FamilySignUpRequest familySignUpRequest) { //리팩토링 필요할것 같음.
        familyAccountFacade.signUp(familySignUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest){
        JwtToken jwtToken = familyAccountFacade.signIn(signInRequest);

        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", "Bearer " + accessToken);
        headers.add("refresh-token", "Bearer " + refreshToken);

        return ResponseEntity.ok().headers(headers).build();
    }
}
