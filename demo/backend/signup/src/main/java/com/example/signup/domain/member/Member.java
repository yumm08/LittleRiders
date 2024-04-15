package com.example.signup.domain.member;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String email;

    private String name;


    @Setter
    private String password;

    private Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static Member of(String email, String name, String password) {
        return new Member(email, name, password);
    }


}
