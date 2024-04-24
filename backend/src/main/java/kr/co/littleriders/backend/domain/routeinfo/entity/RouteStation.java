package kr.co.littleriders.backend.domain.routeinfo.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.station.entity.Station;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",nullable = false)
    private Academy academy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id",nullable = false)
    private Station station;

    @Column(name = "visit_order" , nullable = false)
    private Integer visitOrder;

//    @OneToMany(mappedBy = "boardRouteStation")
//    private List<ChildBoardDropInfo> childBoardInfoList;
//
//    @OneToMany(mappedBy = "dropRouteStation")
//    private List<ChildBoardDropInfo> childDropInfoList;

}
