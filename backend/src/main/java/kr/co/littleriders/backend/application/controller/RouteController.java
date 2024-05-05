package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academy/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteFacade routeFacade;

    // 경로 등록
    @PostMapping
    public ResponseEntity<Void> createRoute(@Auth AuthAcademy authAcademy, @RequestBody RouteRequest createRequest) {
        routeFacade.createRoute(authAcademy, createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // 노선 수정
    @PutMapping("/{route_id}")
    public ResponseEntity<Void> updateStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId, @RequestBody RouteRequest routeRequest) {
        long academyId = authAcademy.getId();
        routeFacade.updateRoute(academyId, routeId, routeRequest);
        return ResponseEntity.ok().build();
    }

    // 노선 삭제
    @DeleteMapping("/{route_id}")
    public ResponseEntity<Void> deleteStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId) {
        long academyId = authAcademy.getId();
        routeFacade.deleteRoute(academyId, routeId);
        return ResponseEntity.ok().build();
    }

}
