package kr.co.littleriders.backend.domain.stationroute.service;

import kr.co.littleriders.backend.domain.stationroute.StationRouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class StationRouteServiceImpl implements StationRouteService {
    private final StationRouteRepository stationRouteRepository;

}
