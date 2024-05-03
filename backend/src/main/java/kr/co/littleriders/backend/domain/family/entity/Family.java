package kr.co.littleriders.backend.domain.family.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.global.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "family")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Family implements Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 보호자 id


    @Column(name = "email", nullable = false,unique = true)
    private String email; // 이메일


    @Setter
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호


    @Column(name = "name", nullable = false)
    private String name; // 성명


    @Column(name = "address",nullable = false)
    private String address; //주소


    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 연락처

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
