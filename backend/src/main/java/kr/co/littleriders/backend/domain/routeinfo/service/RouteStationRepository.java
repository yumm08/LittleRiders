package kr.co.littleriders.backend.domain.routeinfo.service;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface RouteStationRepository extends JpaRepository<RouteStation,Long> {
}
