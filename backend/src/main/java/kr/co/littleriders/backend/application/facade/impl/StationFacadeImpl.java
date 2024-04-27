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
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class StationFacadeImpl implements StationFacade {

    private final StationService stationService;
    private final AcademyService academyService;

    @Override
    public void createStation(AuthAcademy authAcademy, StationCreateRequest createRequest) {
        Long academyId = authAcademy.getId();
        Academy academy = academyService.findById(academyId);

        String name = createRequest.getName();
        if (stationService.existsByAcademyIdAndName(academyId, name)) {
            throw StationException.from(StationErrorCode.DUPLICATE_NAME);
        }
        Station station = createRequest.toStation(academy);
        stationService.save(station);
    }

    @Override
    public List<StationResponse> searchByName(String name, AuthAcademy authAcademy) {
        Long academyId = authAcademy.getId();
        List<Station> stationList = stationService.findAllByAcademyIdAndName(academyId, name);
        return stationList.stream()
                .map(StationResponse::from)
                .collect(Collectors.toList());
    }

}
