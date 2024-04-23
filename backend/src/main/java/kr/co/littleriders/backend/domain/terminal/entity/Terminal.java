package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;

@Entity
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
