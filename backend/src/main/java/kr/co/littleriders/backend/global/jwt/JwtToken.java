package kr.co.littleriders.backend.global.jwt;


import lombok.Getter;


@Getter
public class JwtToken {

    private final String accessToken;

    private final String refreshToken;

    private JwtToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtToken of(String accessToken, String refreshToken) {
        return new JwtToken(accessToken, refreshToken);
    }
}
