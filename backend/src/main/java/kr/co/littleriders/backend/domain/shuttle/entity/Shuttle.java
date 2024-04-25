package kr.co.littleriders.backend.domain.shuttle.entity;

import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "shuttle")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shuttle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 차량 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "license_number", nullable = false)
    private String licenseNumber; // 차량번호

    @Column(name = "type")
    private String type; // 차종

    @Column(name = "name")
    private String name; // 차량명

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShuttleStatus status; // 상태

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @OneToOne(mappedBy = "shuttle")
    private ShuttleTerminalAttach shuttleTerminalAttach; // 차량 단말기 부착 정보

    private Shuttle(String licenseNumber, String name, String type, Academy academy, ShuttleStatus status) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.type = type;
        this.academy = academy;
        this.status = status;
    }

    private Shuttle(String licenseNumber, String name, String type, Academy academy, ShuttleStatus status, String imagePath) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.type = type;
        this.academy = academy;
        this.status = status;
        this.imagePath = imagePath;
    }

    public static Shuttle of(ShuttleRegistRequest shuttleRegistRequest, Academy academy) {
        return new Shuttle(shuttleRegistRequest.getLicenseNumber()
                            , shuttleRegistRequest.getName()
                            , shuttleRegistRequest.getType()
                            , academy
                            , ShuttleStatus.USE);
    }

    public static Shuttle of(ShuttleRegistRequest shuttleRegistRequest, Academy academy, ShuttleStatus status, String imagePath) {
        return new Shuttle(shuttleRegistRequest.getLicenseNumber()
            , shuttleRegistRequest.getName()
            , shuttleRegistRequest.getType()
            , academy
            , status
            , imagePath);
    }
}
