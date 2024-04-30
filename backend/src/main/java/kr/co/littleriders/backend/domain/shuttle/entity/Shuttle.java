package kr.co.littleriders.backend.domain.shuttle.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import lombok.Getter;

@Getter
@Entity
@Table(name = "shuttle")
public class Shuttle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 차량 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "license_number", nullable = false)
    private Integer licenseNumber; // 차량번호

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

}
