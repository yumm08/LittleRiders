package kr.co.littleriders.backend.domain.routeinfo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "child_board_drop_info")
public class ChildBoardDropInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "board_route_station_id",nullable = false)
    private RouteStation boardRouteStation;

    @ManyToOne
    @JoinColumn(name = "drop_route_station_id",nullable = false)
    private RouteStation dropRouteStation;



    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_the_week",nullable = false)
    private ChildBoardDropInfoDayOfTheWeek dayOfTheWeek;






}
