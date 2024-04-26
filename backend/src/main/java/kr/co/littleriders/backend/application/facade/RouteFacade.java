package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteCreateRequest;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

import java.util.List;

public interface RouteFacade {

    void createRoute(Academy academy, RouteCreateRequest createRequest);

    List<RouteResponse> getAllRoute(Academy academy);

    RouteResponse getRoute(Academy academy, Long RouteId);

}
