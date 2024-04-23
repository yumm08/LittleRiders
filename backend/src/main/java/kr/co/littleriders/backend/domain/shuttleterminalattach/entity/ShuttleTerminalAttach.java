package kr.co.littleriders.backend.domain.shuttleterminalattach.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shuttle_terminal_attach")
public class ShuttleTerminalAttach {

    // 차량 단말기 부착정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "shuttleId")
//    private Shuttle shuttle;
//
//    @OneToOne
//    @JoinColumn(name = "terminalId")
//    private Terminal terminal;
}
