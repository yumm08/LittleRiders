package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family/account")
@RequiredArgsConstructor
public class FamilyAccountController {
    private final FamilyAccountFacade familyAccountFacade;


    @GetMapping("/validate")
    public ResponseEntity<?> sendSignUpVerificationMail(@RequestParam String email){
        familyAccountFacade.sendSignUpEmail(email);
        return ResponseEntity.ok().build();
    }
}
