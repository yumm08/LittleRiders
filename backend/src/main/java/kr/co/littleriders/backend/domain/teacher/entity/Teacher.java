package kr.co.littleriders.backend.domain.teacher.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "teacher")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 선생님 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 전화번호

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @Column(name = "status", nullable = false)
    private TeacherStatus status; // 상태

    @Column(name = "card_number")
    private String cardNumber; // 카드정보

    public Teacher(Academy academy, String name, String phoneNumber, TeacherStatus status) {
        this.academy = academy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Teacher(Academy academy, String name, String phoneNumber, TeacherStatus status, String imagePath) {
        this.academy = academy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.imagePath = imagePath;
    }


    public static Teacher of(TeacherRegistRequest teacherRegistRequest, Academy academy, TeacherStatus status) {
        return new Teacher(academy
                        , teacherRegistRequest.getName()
                        , teacherRegistRequest.getPhoneNumber()
                        , status);
	}

    public static Teacher of(TeacherRegistRequest teacherRegistRequest, Academy academy, TeacherStatus status, String imagePath) {
        return new Teacher(academy
            , teacherRegistRequest.getName()
            , teacherRegistRequest.getPhoneNumber()
            , status
            , imagePath);
    }
}
