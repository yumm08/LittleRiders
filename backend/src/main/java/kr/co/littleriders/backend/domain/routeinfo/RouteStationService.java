package kr.co.littleriders.backend.domain.routeinfo;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;


public interface RouteStationService {

    RouteStation findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(RouteStation routeStation);


}
