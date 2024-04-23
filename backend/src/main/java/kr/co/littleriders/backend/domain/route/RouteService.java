package kr.co.littleriders.backend.domain.route;

import kr.co.littleriders.backend.domain.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteService {
    Route findById(Long id);


    boolean existsById(Long id);

    boolean notExistsById(Long id);

}
