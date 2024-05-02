package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RouteFacadeImpl implements RouteFacade {

    private final RouteService routeService;
    private final AcademyService academyService;

    @Override
    public void createRoute(AuthAcademy authAcademy, RouteRequest routeRequest) {
        long academyId = authAcademy.getId();
        Academy academy = academyService.findById(academyId);

        String name = routeRequest.getName();
        if (routeService.existsByAcademyIdAndName(academyId, name)) {
            throw RouteException.from(RouteErrorCode.DUPLICATE_NAME);
        }

        Route route = routeRequest.toRoute(academy);

        routeService.save(route);
    }

}
