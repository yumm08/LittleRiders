package kr.co.littleriders.backend.application.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.littleriders.backend.application.facade.AccountFacade;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountFacade accountFacade;

    @GetMapping("/re-issue")
    public ResponseEntity<Void> reIssueTokenByRefresh(@CookieValue("refresh-token") String requestRefreshToken, HttpServletResponse response){
        JwtToken jwtToken = accountFacade.tokenReIssue(requestRefreshToken);
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

    @GetMapping("/sign-out")
    public ResponseEntity<Void> signOut(@CookieValue("refresh-token") String requestRefreshToken, HttpServletResponse response){
        accountFacade.signOut(requestRefreshToken);
        Cookie cookie = new Cookie("refresh-token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }


}
