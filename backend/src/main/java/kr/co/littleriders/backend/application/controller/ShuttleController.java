package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.RouteDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shuttle")
@RequiredArgsConstructor
public class ShuttleController {
    private final ShuttleFacade shuttleFacade;

    // 운행 가능 노선 목록 조회
    @GetMapping("/route")
    public ResponseEntity<List<ShuttleRouteResponse>> getRouteList(@Auth AuthTerminal authTerminal) {
        return ResponseEntity.ok().body(shuttleFacade.getRouteList(authTerminal));
    }

    // 운행 가능 노선 상세 조회
    @GetMapping("/route/{route_id}")
    public ResponseEntity<RouteDetailResponse> getRoute(@Auth AuthTerminal authTerminal, @PathVariable(name = "route_id") long routeId) {
        return ResponseEntity.ok().body(shuttleFacade.getRoute(authTerminal, routeId));
    }

    // 운행 시작
    @PostMapping("/start")
    public ResponseEntity<Void> startDrive(@Auth AuthTerminal authTerminal, @RequestBody ShuttleStartRequest startRequest) {
        shuttleFacade.startDrive(authTerminal, startRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 운행 종료
    @PostMapping("/end")
    public ResponseEntity<Void> endDrive(@Auth AuthTerminal authTerminal) {
        shuttleFacade.endDrive(authTerminal.getShuttleId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 원생 승하차
    @PostMapping("/child/ride")
    public ResponseEntity<Void> recordChildRiding(@Auth AuthTerminal authTerminal, @RequestBody ShuttleChildRideRequest rideRequest) {
        shuttleFacade.recordChildRiding(authTerminal, rideRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 위도 경도 업로드
    @PostMapping("/location")
    public ResponseEntity<Void> uploadLocation(@Auth AuthTerminal authTerminal, @RequestBody ShuttleLocationRequest locationRequest) {
        shuttleFacade.uploadLocation(authTerminal, locationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
