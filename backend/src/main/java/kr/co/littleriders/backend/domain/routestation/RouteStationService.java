package kr.co.littleriders.backend.domain.routestation;

import kr.co.littleriders.backend.domain.routestation.entity.RouteStation;


public interface RouteStationService {

    RouteStation findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);
}
