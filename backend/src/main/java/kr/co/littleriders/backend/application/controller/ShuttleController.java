package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
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
    public ResponseEntity<List<ShuttleRouteResponse>> getRouteList() {
        return ResponseEntity.ok().body(shuttleFacade.getRouteList());
    }

    // 운행 시작
    @PostMapping("/start")
    public ResponseEntity<Void> startDrive(@RequestBody ShuttleStartRequest startRequest) {
        shuttleFacade.startDrive(startRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 운행 종료
    @PostMapping("/end")
    public ResponseEntity<Void> endDrive() {
        long shuttleId = 1;
        shuttleFacade.endDrive(shuttleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 원생 승하차
    @PostMapping("/child/ride")
    public ResponseEntity<Void> recordChildRiding(@RequestBody ShuttleChildRideRequest rideRequest) {
        shuttleFacade.recordChildRiding(rideRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 위도 경도 업로드
    @PostMapping("/location")
    public ResponseEntity<Void> uploadLocation(@RequestBody ShuttleLocationRequest locationRequest) {
        shuttleFacade.uploadLocation(locationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
