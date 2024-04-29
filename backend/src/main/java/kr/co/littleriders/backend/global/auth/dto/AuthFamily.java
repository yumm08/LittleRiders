package kr.co.littleriders.backend.global.auth.dto;

import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.Getter;


@Getter
public class AuthFamily implements AuthDTO {

    private final long id;
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final String address;

    private AuthFamily(long id, String email, String name, String phoneNumber, String address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static AuthFamily from(Family family) {
        long id = family.getId();
        String email = family.getEmail();
        String name = family.getName();
        String phoneNumber = family.getPhoneNumber();
        String address = family.getAddress();
        return new AuthFamily(id, email, name, phoneNumber, address);
    }
}
