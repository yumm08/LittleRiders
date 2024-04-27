package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/station")
@RequiredArgsConstructor
public class StationController {
    private final StationFacade stationFacade;

    // 정류장 등록
    @PostMapping
    public ResponseEntity<?> createStation(@RequestBody StationCreateRequest createRequest) {
        Academy academy = null;
        stationFacade.createStation(academy, createRequest);
        return ResponseEntity.ok().build();
    }

    // 정류장 목록 조회
    @GetMapping
    public ResponseEntity<?> searchStationByName(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Academy academy = null;
        return ResponseEntity.ok().body(stationFacade.searchByName(name, academy));
    }
}
