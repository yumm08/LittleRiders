package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteCreateRequest;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;

import java.util.List;

public interface RouteFacade {

    void createRoute(AuthAcademy authAcademy, RouteCreateRequest createRequest);

    List<RouteResponse> getAllRoute(AuthAcademy authAcademy);

    RouteResponse getRoute(AuthAcademy authAcademy, Long RouteId);

}
