package kr.co.littleriders.backend.domain.family.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.global.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "family")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Family implements Member {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 보호자 id


    @Getter
    @Column(name = "email", nullable = false,unique = true)
    private String email; // 이메일


    @Getter
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호


    @Getter
    @Column(name = "name", nullable = false)
    private String name; // 성명


    @Getter
    @Column(name = "address",nullable = false)
    private String address; //주소


    @Getter
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 연락처

    @Getter
    @OneToMany(mappedBy = "family")
    private List<Child> child; // 자녀 목록

    private Family(String email, String password, String name,String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static Family of(String email, String password, String name,String address, String phoneNumber) {
        return new Family(email, password, name,address, phoneNumber);
    }

}
