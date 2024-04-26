package kr.co.littleriders.backend.domain.station.service;


import kr.co.littleriders.backend.domain.station.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StationRepository extends JpaRepository<Station,Long> {
    Page<Station> findAllByAcademyIdAndNameContaining(Long academyId, String name, Pageable pageable);

    boolean existsByAcademyIdAndName(Long academyId, String name);
}
