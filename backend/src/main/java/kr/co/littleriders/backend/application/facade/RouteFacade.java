package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;

public interface RouteFacade {
    void createRoute(AuthAcademy authAcademy, RouteRequest routeRequest);

    void updateRoute(long academyId, long routeId, RouteRequest routeRequest);

    void deleteRoute(long academyId, long routeId);
}
