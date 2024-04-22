package kr.co.littleriders.backend.domain.stationroute.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class StationRouteServiceImpl {
    private final StationRouteRepository stationRouteRepository;

}
