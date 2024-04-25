package kr.co.littleriders.backend.global.jwt;


import lombok.Getter;


@Getter
public class JwtToken {

    private final String accessToken;

    private final long accessTokenExpTime;


    private final String refreshToken;

    private final long refreshTokenExpTime;

    private JwtToken(String accessToken, long accessTokenExpTime, String refreshToken, long refreshTokenExpTime) {
        this.accessToken = accessToken;
        this.accessTokenExpTime = accessTokenExpTime;
        this.refreshToken = refreshToken;
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public static JwtToken of(String accessToken, long accessTokenExpTime, String refreshToken, long refreshTokenExpTime) {

        return new JwtToken(accessToken, accessTokenExpTime, refreshToken, refreshTokenExpTime);
    }
}
