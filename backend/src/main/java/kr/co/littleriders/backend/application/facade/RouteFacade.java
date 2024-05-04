package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;

import java.util.List;

public interface RouteFacade {
    void createRoute(AuthAcademy authAcademy, RouteRequest routeRequest);

    void addRouteStation(long academyId, long routeId, List<RouteStationRequest> requestList);

    void addAcademyChildToRouteStation(long academyId, long routeId, List<RouteStationAcademyChildRequest> requestList);

    List<RouteResponse> getAllRoute(long academyId);
}
