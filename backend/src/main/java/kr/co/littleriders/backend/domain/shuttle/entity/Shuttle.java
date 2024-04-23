package kr.co.littleriders.backend.domain.shuttle.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttleterminalattach.entity.ShuttleTerminalAttach;

@Entity
@Table(name = "shuttle")
public class Shuttle {

    // 버스id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 학원id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("academyId")
    @JoinColumn(name = "academyId")
    private Academy academy;

    // 차량번호
    @Column(name = "license_number", nullable = false)
    private Integer licenseNumber;

    // 차종
    @Column(name = "type")
    private String type;

    // 버스명
    @Column(name = "name")
    private String name;

    // 상태
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    // 이미지 경로
    @Column(name = "image_path")
    private String imagePath;

//    // 차량 단말기 부착 정보
//    @OneToOne(mappedBy = "shuttleId")
//    private ShuttleTerminalAttach shuttleTerminalAttach;

}
