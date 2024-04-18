package jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtService {




    private final long ACCESS_TOKEN_EXPIRE_TIME = 3000;

    private final long REFRESH_TOKEN_EXPIRE_TIME = 9000;


    private final SecretKey ACCESS_SECRET_KEY;
    private final SecretKey REFRESH_SECRET_KEY;


    private static JwtService instance;

    private JwtService() {
        this.ACCESS_SECRET_KEY = new SecretKeySpec("ACCESS_SECRET_KEY_1234123123123123".getBytes(), SignatureAlgorithm.HS512.getJcaName());
        this.REFRESH_SECRET_KEY = new SecretKeySpec("REFRESH_SECRET_KEY_123123123123123".getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    public static JwtService getInstance() {
        if(instance == null){
            instance = new JwtService();
        }
        return instance;
    }


    private String createToken(int id, long now,long token_expired_time, SecretKey secretKey) {
        return Jwts.builder()
                .setSubject(id+"")
                .setExpiration(new Date(now + token_expired_time))
                .signWith(secretKey)
                .compact();
    }

    private String createAccessToken(int id, long now){
        return createToken(id,now,ACCESS_TOKEN_EXPIRE_TIME,ACCESS_SECRET_KEY);
    }

    private String createRefreshToken(int id, long now){
        return createToken(id,now,REFRESH_TOKEN_EXPIRE_TIME,REFRESH_SECRET_KEY);
    }



    public Jwt createAccessAndRefreshToken(int id) {
        long now = System.currentTimeMillis();
        String accessToken = createAccessToken(id,now);
        String refreshToken = createRefreshToken(id,now);

        return new Jwt(accessToken,refreshToken);

    }
}
