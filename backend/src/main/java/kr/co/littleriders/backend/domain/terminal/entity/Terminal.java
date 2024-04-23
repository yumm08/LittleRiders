package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttleterminalattach.entity.ShuttleTerminalAttach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terminal")
public class Terminal {

    // 단말기id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    // 학원id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "academy_id")
//    private Academy academy;

    // 단말기 고유번호
    @Column(name = "terminal_number")
    private String terminalNumber;

//    // 차량 단말기 부착정보
//    @OneToOne(mappedBy = "terminal_id")
//    private ShuttleTerminalAttach shuttleTerminalAttach;
}
