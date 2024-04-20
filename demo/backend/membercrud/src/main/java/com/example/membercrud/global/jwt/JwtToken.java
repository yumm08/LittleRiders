package com.example.membercrud.global.jwt;
import lombok.Getter;

@Getter
public class JwtToken {

    private final String accessToken;
    private final String refreshToken;

    private JwtToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    protected static JwtToken of(String accessToken, String refreshToken){
        return new JwtToken(accessToken,refreshToken);
    }
}
