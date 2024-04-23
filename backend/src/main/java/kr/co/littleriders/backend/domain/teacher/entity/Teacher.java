package kr.co.littleriders.backend.domain.teacher.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

@Entity
@Table(name = "teacher")
public class Teacher {

    // 선생님id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 학원id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;

    // 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 전화번호
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // 이미지 경로
    @Column(name = "image_path")
    private String imagePath;

    // 상태
    @Column(name = "status", nullable = false)
    private TeacherStatus status;

    // 카드정보
    @Column(name = "card_number")
    private String cardNumber;

}
