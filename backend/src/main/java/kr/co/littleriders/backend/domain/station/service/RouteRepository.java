package kr.co.littleriders.backend.domain.station.service;


import kr.co.littleriders.backend.domain.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RouteRepository extends JpaRepository<Station,Long> {
}
