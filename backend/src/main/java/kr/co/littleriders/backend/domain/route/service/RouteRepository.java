package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.domain.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface RouteRepository extends JpaRepository<Route,Long> {
}
