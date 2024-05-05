package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;

import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class RouteFacadeTest {
    @Autowired
    private RouteFacade routeFacade;

    @Autowired
    private AcademyService academyService;
    private Academy academy;
    private AuthAcademy authAcademy;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @BeforeEach
    void setUp() {
        academy = Academy.of("a123@gmail.com", "1234", "어린이집A", "서울시 강남구", "010-1111-1111", 35.6, 23.8);
        academyService.save(academy);
        authAcademy = AuthAcademy.from(academy);
    }

    @Nested
    @DisplayName("노선 등록 테스트")
    class createRoute {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            RouteRequest routeRequest = new RouteRequest("등원A", "board");
            routeFacade.createRoute(academy.getId(), routeRequest);
        }
    }

    @Nested
    @DisplayName("노선별 정류장 추가 테스트")
    class addRouteStation {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            long academyId = academy.getId();

            Route route = Route.of(academy, "등원A", "board");
            long routeId = routeService.save(route);

            Station station1 = Station.of(academy, "정류장A", 13.4, 13.5);
            long stationId1 = stationService.save(station1);
            Station station2 = Station.of(academy, "정류장B", 23.4, 23.5);
            long stationId2 = stationService.save(station2);
            Station station3 = Station.of(academy, "정류장C", 33.4, 33.5);
            long stationId3 = stationService.save(station3);

            List<RouteStationRequest> requestList = new ArrayList<>();
            RouteStationRequest request1 = new RouteStationRequest(stationId2, 3);
            RouteStationRequest request2 = new RouteStationRequest(stationId1, 1);
            RouteStationRequest request3 = new RouteStationRequest(stationId3, 2);
            requestList.add(request1);
            requestList.add(request2);
            requestList.add(request3);

            routeFacade.addRouteStation(academyId, routeId, requestList);
        }
    }

}
