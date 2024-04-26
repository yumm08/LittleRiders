package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RouteCreateRequest {
    private String name;
    private List<RouteStation> stationList;

    public Route toRoute(Academy academy) {
        return Route.of(academy, this.name, this.stationList);
    }
}
