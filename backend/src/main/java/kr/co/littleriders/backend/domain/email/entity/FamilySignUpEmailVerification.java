package kr.co.littleriders.backend.domain.email.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "family_sign_up_email_verification")
@Getter
public class FamilySignUpEmailVerification {

    @Id
    private String email;
    private String code;

    @TimeToLive
    private long ttl;

    private FamilySignUpEmailVerification(final String email) {
        this.email = email;
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        this.code = generator.nextInt(1000000) % 1000000 + "";
        this.ttl = 60 * 3;
    }

    public static FamilySignUpEmailVerification from(final String email) {
        return new FamilySignUpEmailVerification(email);
    }

    public boolean EqualsCode(final String email) {
        return this.code.equals(email);
    }

    public boolean notEqualsCode(final String email) {
        return !this.EqualsCode(email);
    }
}
