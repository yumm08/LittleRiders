package kr.co.littleriders.backend.domain.history.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "child_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 원생 이력 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child; // 자녀

    @Column(name = "name", nullable = false)
    private String name; // 성명

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate; // 생년월일

    @Column(name = "gender", nullable = false)
    private Gender gender; // 성별

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 생성일자

}
