package kr.co.littleriders.backend.domain.routestation.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.station.entity.Station;

@Entity
@Table(name = "station_route",
uniqueConstraints = {
        @UniqueConstraint(
                name = "station_route_does_not_duplicate_in_field",
                columnNames = {"route_id","station_id","academy_id","visit_order"}
        )
})
public class RouteStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;



//    @ManyToOne
    @Column(name = "academy_id",nullable = false)
    private Long academy; //TODO : 학원 엔티티로 매핑 필요
    @ManyToOne
    @JoinColumn(name = "station_id",nullable = false)
    private Station station;


    @Column(name = "visit_order" , nullable = false)
    private Integer visitOrder;



}
