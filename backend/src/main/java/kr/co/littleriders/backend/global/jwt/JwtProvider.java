package kr.co.littleriders.backend.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.littleriders.backend.global.entity.MemberType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtProvider {


    private final long ACCESS_TOKEN_EXPIRE_TIME;


    private final long REFRESH_TOKEN_EXPIRE_TIME;

    private final String MEMBER_TYPE = "MEMBER_TYPE";

    private final SecretKey ACCESS_SECRET_KEY;
    private final SecretKey REFRESH_SECRET_KEY;

    public JwtProvider(@Value("${spring.jwt.access.expTime}") long accessTokenExpireTime, @Value("${spring.jwt.refresh.expTime}") long refreshTokenExpireTime, @Value("${spring.jwt.access.secret}") String accessKey, @Value("${spring.jwt.refresh.secret}") String refreshKey) {

        this.ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;
        this.REFRESH_TOKEN_EXPIRE_TIME = refreshTokenExpireTime;

        this.ACCESS_SECRET_KEY = new SecretKeySpec(accessKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        this.REFRESH_SECRET_KEY = new SecretKeySpec(refreshKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    public JwtToken createToken(Long id, MemberType memberType) {


        long now = System.currentTimeMillis();
        String userId = id.toString();
        String accessToken = createAccessToken(userId, now, memberType);
        String refreshToken = createRefreshToken(userId, now, memberType);

        return JwtToken.of(accessToken, ACCESS_TOKEN_EXPIRE_TIME, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String createAccessToken(String userId, long now, MemberType memberType) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(MEMBER_TYPE, memberType.name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(ACCESS_SECRET_KEY)
                .compact();
    }

    private String createRefreshToken(String userId, long now, MemberType memberType) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(MEMBER_TYPE, memberType.name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(REFRESH_SECRET_KEY)
                .compact();
    }
//
//    public Member getMember(String token, MemberType memberType) {
//        token = token.replace("Bearer ", "");
//        Claims claims = validationAndparesClaimsByAccessToken(token);
//        String id = claims.getSubject();
//        MemberType tokenMemberType = MemberType.valueOf(claims.get(MEMBER_TYPE).toString());
//
//        if (tokenMemberType == MemberType.ACADEMY) {
//
//        }
//
//
//    }
//
//    private Claims validationAndparesClaimsByAccessToken(String token) {
//        try {
//            token = token.replace("Bearer ", "");
//            Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_SECRET_KEY).build().parseClaimsJws(token).getBody();
//            if (claims.get(MEMBER_TYPE) == null) {
//                throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_AUTHORIZATION_INFORMATION);
//            }
//            return claims;
//        } catch (ExpiredJwtException e) {
//            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_KEY_EXPIERD);
//        } catch (UnsupportedJwtException e) {
//            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_NOT_SUPPORT);
//        } catch (IllegalArgumentException e) {
//            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_KEY_INVALID);
//        }
//    }


}

