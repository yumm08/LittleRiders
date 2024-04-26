package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.RouteCreateRequest;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteFacade routeFacade;

    // 경로 등록
    @PostMapping
    public ResponseEntity<?> createRoute(@RequestBody RouteCreateRequest createRequest) {
        Academy academy = null;
        routeFacade.createRoute(academy, createRequest);
        return ResponseEntity.ok().build();
    }

    // 경로 목록 조회
    @GetMapping
    public ResponseEntity<?> getRouteList() {
        Academy academy = null;
        return ResponseEntity.ok().body(routeFacade.getAllRoute(academy));
    }

    // 경로 상세 조회
    @GetMapping("/{route_id}")
    public ResponseEntity<?> getRoute(@PathVariable(name = "route_id") Long routeId) {
        Academy academy = null;
        return ResponseEntity.ok().body(routeFacade.getRoute(academy, routeId));
    }

}
