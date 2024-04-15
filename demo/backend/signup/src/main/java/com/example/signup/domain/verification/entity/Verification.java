package com.example.signup.domain.verification.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "verification")
@Getter
public class Verification {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private long ttl;

    @Enumerated(EnumType.STRING)
    private VerificationType type;


    private Verification(String email, VerificationType type) {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        this.code = generator.nextInt(1000000) % 1000000 + "";
        this.email = email;
        this.ttl = 30;
        this.type =type;
    }


    public static Verification of(String email,VerificationType verificationType){
        return new Verification(email,verificationType);
    }


    public boolean isNotEqualsCode(String code){
        return !this.isEqualsCode(code);
    }

    public boolean isNotEqualsType(VerificationType verificationType){
        return !this.isEqualsType(verificationType);
    }

    public boolean isEqualsCode(String code){
        return this.getCode().equals(code);
    }

    public boolean isEqualsType(VerificationType verificationType){
        return this.type == verificationType;
    }
}
