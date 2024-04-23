package kr.co.littleriders.backend.domain.station;

import kr.co.littleriders.backend.domain.station.entity.Station;

public interface StationService {


    Station findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);
}
