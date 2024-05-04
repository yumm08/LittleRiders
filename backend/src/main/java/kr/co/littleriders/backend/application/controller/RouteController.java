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
    public ResponseEntity<Void> createRoute(@Auth AuthAcademy authAcademy, @RequestBody RouteRequest routeRequest) {
        routeFacade.createRoute(authAcademy, routeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}