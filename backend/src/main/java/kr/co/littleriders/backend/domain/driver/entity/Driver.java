package kr.co.littleriders.backend.domain.driver.entity;

import java.util.UUID;

import jakarta.persistence.*;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "driver")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Enumerated(EnumType.STRING)
    private DriverStatus status; // 상태

    @Column(name = "card_number", nullable = false)
    private String cardNumber; // 카드정보

    private Driver(Academy academy, String name, String phoneNumber, DriverStatus status, String imagePath, String code) {
        this.academy = academy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.imagePath = imagePath;
        this.cardNumber = code;
    }

    private Driver(Academy academy, String name, String phoneNumber, DriverStatus status, String code) {
        this.academy = academy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.cardNumber = code;
    }

    public static Driver of(DriverRegistRequest driverRegistRequest, Academy academy, DriverStatus driverStatus, String imagePath) {
        return new Driver(academy
                        , driverRegistRequest.getName()
                        , driverRegistRequest.getPhoneNumber()
                        , driverStatus
                        , imagePath
                        , generateCode());
    }

    public static Driver of(DriverRegistRequest driverRegistRequest, Academy academy, DriverStatus driverStatus) {
        return new Driver(academy
            , driverRegistRequest.getName()
            , driverRegistRequest.getPhoneNumber()
            , driverStatus
            , generateCode());
    }

    private static String generateCode() {
        return UUID.randomUUID().toString();
    }
}
