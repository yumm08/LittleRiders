package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.response.ValidateEmailResponse;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
