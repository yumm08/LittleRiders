package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.domain.station.error.exception.StationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StationFacadeImpl implements StationFacade {

    private final StationService stationService;
    private final AcademyService academyService;

    @Override
    public void createStation(Academy academyDto, StationCreateRequest createRequest) {
        Long academyId = academyDto.getId();
        Academy academy = academyService.findById(academyId);

        String name = createRequest.getName();
        if (stationService.existsByAcademyIdAndName(academyId, name)) {
            throw StationException.from(StationErrorCode.DUPLICATE_NAME);
        }
        Station station = createRequest.toStation(academy);
        stationService.save(station);
    }

    @Override
    public Page<StationResponse> searchByName(String name, Academy academyDto, Pageable pageable) {
        Long academyId = academyDto.getId();
        Page<Station> stationList = stationService.findAllByAcademyIdAndName(academyId, name, pageable);
        return stationList.map(StationResponse::from);
    }

}
