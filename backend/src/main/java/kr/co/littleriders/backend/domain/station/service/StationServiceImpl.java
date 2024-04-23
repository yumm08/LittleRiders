package kr.co.littleriders.backend.domain.station.service;

import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(
                RuntimeException::new //TODO : 커스텀 익셉션 변경 필요
        );
    }

    @Override
    public boolean existsById(Long id) {
        return stationRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !stationRepository.existsById(id);
    }
}
