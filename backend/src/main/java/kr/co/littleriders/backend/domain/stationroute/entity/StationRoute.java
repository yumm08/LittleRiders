package kr.co.littleriders.backend.domain.stationroute.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.station.entity.Station;

@Entity
public class StationRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "station_id",nullable = false)
    private Station station;


    @Column(name = "visitOrder" , nullable = false)
    private int visitOrder;



}
