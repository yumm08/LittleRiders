package kr.co.littleriders.backend.domain.childboarddropinfo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "child_board_drop_info")
public class ChildBoardDropInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private ChildBoardDropInfoDayOfWeek dayOfWeek;





}
