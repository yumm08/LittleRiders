package kr.co.littleriders.backend.domain.station;

import kr.co.littleriders.backend.domain.station.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationService {


    Station findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    boolean existsByAcademyIdAndName(Long academyId, String name);

    void save(Station station);

    Page<Station> findAllByAcademyIdAndName(Long academyId, String name, Pageable pageable);
}
