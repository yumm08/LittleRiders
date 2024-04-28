package kr.co.littleriders.backend.domain.route.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 경로 id


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",nullable = false)
    private Academy academy; // 학원

    @Column(name = "name",nullable = false)
    private String name; // 경로명

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteStation> routeStationList; // 정류장 목록

    private Route(final Academy academy, String name, List<RouteStation> routeStationList) {
        this.academy = academy;
        this.name = name;
        this.routeStationList = routeStationList;
    }

    public static Route of(final Academy academy, String name, List<RouteStation> routeStationList) {
        return new Route(
                academy,
                name,
                routeStationList
        );
    }

}
