package kr.co.littleriders.backend.domain.child.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "child",
    uniqueConstraints = @UniqueConstraint(
        name = "child_unique",
        columnNames = {"family_id","name","birthDate","gender"}
    ))
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

    private Child(Family family, String name, LocalDate date, Gender gender) {
        this.family = family;
        this.name = name;
        this.birthDate = date;
        this.gender = gender;
    }

    private Child(Family family, String name, LocalDate date, Gender gender, String imagePath) {
        this.family = family;
        this.name = name;
        this.birthDate = date;
        this.gender = gender;
        this.imagePath = imagePath;
    }

    public static Child of(ChildRegistRequest childRegistRequest, Gender gender, Family family) {
        return new Child(family
                        , childRegistRequest.getName()
                        , childRegistRequest.getDate()
                        , gender);
    }

    public static Child of(ChildRegistRequest childRegistRequest, Gender gender, Family family, String imagePath) {
        return new Child(family
            , childRegistRequest.getName()
            , childRegistRequest.getDate()
            , gender
            , imagePath);
    }

}
