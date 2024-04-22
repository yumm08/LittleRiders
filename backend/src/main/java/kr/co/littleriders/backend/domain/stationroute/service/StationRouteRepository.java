package kr.co.littleriders.backend.domain.stationroute.service;

import kr.co.littleriders.backend.domain.stationroute.entity.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface StationRouteRepository extends JpaRepository<StationRoute,Long> {
}
