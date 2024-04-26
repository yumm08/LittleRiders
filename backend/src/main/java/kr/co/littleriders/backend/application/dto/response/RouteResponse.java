package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RouteResponse {
    private long id;
    private String name;
    private List<RouteStation> stationList;

    public static RouteResponse from(Route route) {
        long id = route.getId();
        String name = route.getName();
        List<RouteStation> stationList = route.getRouteStationList();

        return new RouteResponse(id, name, stationList);
    }
}
