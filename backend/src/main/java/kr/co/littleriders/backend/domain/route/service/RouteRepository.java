package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.domain.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
interface RouteRepository extends JpaRepository<Route,Long> {
    boolean existsByAcademyIdAndName(Long academyId, String name);

    List<Route> findAllByAcademyId(Long academyId);
}
