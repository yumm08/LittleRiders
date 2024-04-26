package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.RouteCreateRequest;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteFacadeImpl implements RouteFacade {

    private final RouteService routeService;
    private final AcademyService academyService;

    @Override
    public void createRoute(Academy academyDto, RouteCreateRequest createRequest) {
        Long academyId = academyDto.getId();
        Academy academy = academyService.findById(academyId);

        String name = createRequest.getName();
        if (routeService.existsByAcademyIdAndName(academyId, name)) {
            throw RouteException.from(RouteErrorCode.DUPLICATE_NAME);
        }
        Route route = createRequest.toRoute(academy);
        routeService.save(route);
    }

    @Override
    public List<RouteResponse> getAllRoute(Academy academyDto) {
        Long academyId = academyDto.getId();
        List<Route> routeList = routeService.findAllByAcademyId(academyId);
        return routeList.stream()
                .map(RouteResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RouteResponse getRoute(Academy academyDto, Long routeId) {
        Long academyId = academyDto.getId();
        Route route = routeService.findById(routeId);

        if(!Objects.equals(academyId, route.getAcademy().getId())) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }

        return RouteResponse.from(route);
    }

}
