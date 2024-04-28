package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RouteStationResponse {
    private long id;
    private long stationId;
    private String stationName;
    private double latitude;
    private double longitude;
    private int visitOrder;

    public static RouteStationResponse from(RouteStation routeStation) {
        long id = routeStation.getId();
        long stationId = routeStation.getStation().getId();
        String stationName =routeStation.getStation().getName();
        double latitude = routeStation.getStation().getLatitude();
        double longitude = routeStation.getStation().getLongitude();
        int visitOrder = routeStation.getVisitOrder();

        return new RouteStationResponse(id, stationId, stationName, latitude, longitude, visitOrder);
    }
}
