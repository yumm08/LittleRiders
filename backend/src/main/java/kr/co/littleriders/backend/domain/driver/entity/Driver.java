package kr.co.littleriders.backend.domain.driver.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver")
public class Driver {

    // 기사id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    // 학원id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("academyId")
//    @JoinColumn(name = "academyId")
//    private Academy academy;

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
    private DriverStatus status;

    // 카드정보
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

}
