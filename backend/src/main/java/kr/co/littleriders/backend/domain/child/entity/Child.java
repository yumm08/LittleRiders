package kr.co.littleriders.backend.domain.child.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.family.entity.Family;
import org.springframework.format.annotation.DateTimeFormat;

import kr.co.littleriders.backend.domain.common.Gender;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 자녀 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family; // 보호자

    @Column(name = "name", nullable = false)
    private String name; // 성명

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // 생년월일

    @Column(name = "gender", nullable = false)
    private Gender gender; // 성별

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

}
