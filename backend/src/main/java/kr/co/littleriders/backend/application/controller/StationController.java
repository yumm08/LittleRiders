package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/station")
@RequiredArgsConstructor
public class StationController {
    private final StationFacade stationFacade;

    // 정류장 등록
    @PostMapping
    public ResponseEntity<Void> createStation(@Auth AuthAcademy authAcademy, @RequestBody StationCreateRequest createRequest) {
        stationFacade.createStation(authAcademy, createRequest);
        return ResponseEntity.ok().build();
    }

    // 정류장 목록 조회
    @GetMapping
    public ResponseEntity<List<StationResponse>> searchStationByName(@Auth AuthAcademy authAcademy, @RequestParam(required = false, defaultValue = "") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(stationFacade.searchByName(name, authAcademy));
    }
}
