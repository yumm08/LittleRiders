package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.routeinfo.RouteStationService;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class RouteFacadeImpl implements RouteFacade {

    private final RouteService routeService;
    private final AcademyService academyService;
    private final StationService stationService;
    private final RouteStationService routeStationService;

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

    @Override
    public void addRouteStation(long academyId, long routeId, List<RouteStationRequest> requestList) {
        Academy academy = academyService.findById(academyId);
        Route route = routeService.findById(routeId);

        // 해당 경로의 기존 정류장 리스트
        List<RouteStation> existingStations = route.getRouteStationList();

        // 기존 정류장 리스트가 비어있다면 새로운 정류장들을 추가
        if (existingStations == null || existingStations.isEmpty()) {
            List<RouteStation> newRouteStationList = requestList.stream()
                    .map(req -> {
                        Station station = stationService.findById(req.getId());
                        RouteStation newRouteStation = req.toRouteStation(route, academy, station);
                        route.addRouteStation(newRouteStation);
                        return newRouteStation;
                    })
                    .collect(Collectors.toList());
            routeStationService.saveAll(newRouteStationList);
        } else {
            // 기존 정류장 리스트와 새로운 정류장 리스트를 비교하여 업데이트 수행
            Map<Long, RouteStation> existingStationMap = existingStations.stream()
                    .collect(Collectors.toMap(rs -> rs.getStation().getId(), Function.identity())); // 키: id, 값:rs

            for (RouteStationRequest req : requestList) {
                Station station = stationService.findById(req.getId());

                RouteStation existingRouteStation = existingStationMap.remove(station.getId());
                if (existingRouteStation == null) { // 기존에 없던 새로운 정류장인 경우
                    RouteStation newRouteStation = req.toRouteStation(route, academy, station);
                    route.addRouteStation(newRouteStation);
                } else { // 기존에 존재하는 정류장인 경우
                    // 방문 순서가 수정되었다면 업데이트
                    if (!Objects.equals(existingRouteStation.getVisitOrder(), req.getVisitOrder())) {
                        existingRouteStation.updateVisitOrder(req.getVisitOrder());
                    }
                }
            }

            // 새로운 정류장 리스트에 포함되지 않은 기존 정류장들을 삭제
            existingStationMap.values().forEach(route::removeRouteStation);

            routeService.save(route);
        }
    }

}
