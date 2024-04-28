package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class RouteResponse {
    private long id;
    private String name;
    private List<RouteStationResponse> routeStationList;

    public static RouteResponse from(Route route) {
        long id = route.getId();
        String name = route.getName();
        List<RouteStationResponse> routeStationList = route.getRouteStationList().stream()
                .map(RouteStationResponse::from)
                .collect(Collectors.toList());

        return new RouteResponse(id, name, routeStationList);
    }
}
