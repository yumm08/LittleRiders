package kr.co.littleriders.backend.domain.route;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;

import java.util.List;

public interface RouteService {
    Route findById(Long id);


    boolean existsById(Long id);

    boolean notExistsById(Long id);

    List<Route> findAllByAcademy(Academy academy);

}
