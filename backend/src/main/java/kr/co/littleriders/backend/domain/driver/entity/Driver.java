package kr.co.littleriders.backend.domain.driver.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 기사 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원 id

    @Column(name = "name", nullable = false)
    private String name; // 성명

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 전화번호

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @Column(name = "status", nullable = false)
    private DriverStatus status; // 상태

    @Column(name = "card_number", nullable = false)
    private String cardNumber; // 카드정보

}
