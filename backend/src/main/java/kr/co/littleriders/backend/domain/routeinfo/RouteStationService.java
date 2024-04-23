package kr.co.littleriders.backend.domain.routeinfo;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;


public interface RouteStationService {

    RouteStation findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);
}
