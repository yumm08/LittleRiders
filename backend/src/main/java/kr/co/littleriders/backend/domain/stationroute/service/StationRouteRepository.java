package kr.co.littleriders.backend.domain.stationroute.service;

import kr.co.littleriders.backend.domain.stationroute.entity.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;

interface StationRouteRepository extends JpaRepository<StationRoute,Long> {
}
