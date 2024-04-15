package com.example.signup.domain.registration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "registration_code")
@Getter
public class RegistrationCode {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private long ttl;


    private RegistrationCode(String code, String email, long ttl) {
        this.code = code;
        this.email = email;
        this.ttl = ttl;
    }


    public static RegistrationCode from(String email) { //코드 책임이 Verification 에 있다.
        String code = UUID.randomUUID().toString();

        return new RegistrationCode(code, email, 60 * 30);

    }

    public boolean isEqualsCode(String code) {
        return this.getCode().equals(code);
    }
    public boolean isNotEqualsCode(String code){
        return !this.isEqualsCode(code);
    }
}
