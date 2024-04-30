package kr.co.littleriders.backend.domain.route;

import kr.co.littleriders.backend.domain.route.entity.Route;

public interface RouteService {
    Route findById(long id);


    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(Route route);

}
