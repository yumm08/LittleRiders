package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StationFacadeImpl implements StationFacade {

    private final StationService stationService;

    @Override
    public void createStation(Academy academy, StationCreateRequest createRequest) {
        Long academyId = academy.getId();

        String name = createRequest.getName();
        if (stationService.existsByName(name, academyId)) {
            throw new RuntimeException();
        }

        Station station = Station.of(academy, createRequest);
        stationService.save(station);
    }

    @Override
    public Page<StationResponse> getAllStationByName(String name, Academy academy, Pageable pageable) {
        Long academyId = academy.getId();
        Page<Station> stationList = stationService.findAllByName(name, academyId, pageable);
        return stationList.map(StationResponse::from);
    }

}
