package com.example.membercrud.domain.family.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Family {
    @Id
    private Long id;

    private String email;
    private String password;
    private String address;
    private String name;
    private String phoneNumber;

//    @OneToMany
//    List<Child> childList;


    private Family(String email, String password, String address, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Family of(String email, String password, String address, String name, String phoneNumber) {
        return new Family(
                email,
                password,
                address,
                name,
                phoneNumber
        );
    }

}
