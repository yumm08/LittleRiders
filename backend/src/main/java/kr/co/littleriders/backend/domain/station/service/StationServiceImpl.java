package kr.co.littleriders.backend.domain.station.service;

import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.domain.station.error.exception.StationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(() -> StationException.from(StationErrorCode.NOT_FOUND));
    }

    @Override
    public boolean existsById(Long id) {
        return stationRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !stationRepository.existsById(id);
    }

    @Override
    public boolean existsByAcademyIdAndName(Long academyId, String name) { return stationRepository.existsByAcademyIdAndName(academyId, name); }

    @Override
    public void save(Station station) {
        stationRepository.save(station);
    }

    @Override
    public List<Station> findAllByAcademyIdAndName(Long academyId, String name) {
        return stationRepository.findAllByAcademyIdAndNameContaining(academyId, name);
    }

}
