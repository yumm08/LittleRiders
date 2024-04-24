package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

@Entity
@Table(name = "terminal")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 단말기 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "terminal_number")
    private String terminalNumber; // 단말기 고유번호

    @OneToOne(mappedBy = "terminal")
    private ShuttleTerminalAttach shuttleTerminalAttach; // 차량 단말기 부착 정보
}
