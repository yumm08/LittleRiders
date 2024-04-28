package kr.co.littleriders.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RouteStationRequest {
    private long stationId;
    private int visitOrder;
}
