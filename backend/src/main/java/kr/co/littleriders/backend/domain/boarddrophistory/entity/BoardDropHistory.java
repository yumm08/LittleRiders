package kr.co.littleriders.backend.domain.boarddrophistory.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "board_drop_history")
public class BoardDropHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    private Academy academy;
//
//    @ManyToOne
//    private AcademyChild academyChild;



    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private BoardDropHistoryStatus status;



    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt; //탑승 시간




}
