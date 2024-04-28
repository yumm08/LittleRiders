package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.RouteCreateRequest;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
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
    public ResponseEntity<?> createRoute(@Auth AuthAcademy authAcademy, @RequestBody RouteCreateRequest createRequest) {
        routeFacade.createRoute(authAcademy, createRequest);
        return ResponseEntity.ok().build();
    }

    // 경로 목록 조회
    @GetMapping
    public ResponseEntity<?> getRouteList(@Auth AuthAcademy authAcademy) {
        return ResponseEntity.ok().body(routeFacade.getAllRoute(authAcademy));
    }

    // 경로 상세 조회
    @GetMapping("/{route_id}")
    public ResponseEntity<?> getRoute(@Auth AuthAcademy authAcademy, @PathVariable(name = "route_id") Long routeId) {
        return ResponseEntity.ok().body(routeFacade.getRoute(authAcademy, routeId));
    }

}
