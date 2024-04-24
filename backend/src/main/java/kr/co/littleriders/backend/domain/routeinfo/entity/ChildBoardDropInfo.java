package kr.co.littleriders.backend.domain.routeinfo.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "child_board_drop_info")
public class ChildBoardDropInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_child_id")
    private AcademyChild academyChild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_route_station_id",nullable = false)
    private RouteStation boardRouteStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drop_route_station_id",nullable = false)
    private RouteStation dropRouteStation;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_the_week",nullable = false)
    private ChildBoardDropInfoDayOfTheWeek dayOfTheWeek;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "board_time")
    private LocalDateTime boardTime;

}
