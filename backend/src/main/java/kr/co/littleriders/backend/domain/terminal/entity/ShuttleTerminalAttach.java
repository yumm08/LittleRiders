package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;

@Entity
@Table(name = "shuttle_terminal_attach")
public class ShuttleTerminalAttach {

    // 차량 단말기 부착정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "shuttle_id")
    private Shuttle shuttle;

    @OneToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;
}
