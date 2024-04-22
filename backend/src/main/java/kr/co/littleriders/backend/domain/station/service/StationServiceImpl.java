package kr.co.littleriders.backend.domain.station.service;

import kr.co.littleriders.backend.domain.station.StationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

}
