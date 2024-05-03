package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public Route findById(long id) {
        return routeRepository.findById(id).orElseThrow(
                () -> RouteException.from(RouteErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(final long id) {
        return routeRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !routeRepository.existsById(id);
    }

    @Override
    public boolean existsByAcademyIdAndName(long academyId, String name) {
        return routeRepository.existsByAcademyIdAndName(academyId, name);
    }

    @Override
    public long save(Route route) {
        return routeRepository.save(route).getId();
    }
    @Override
    public List<Route> findAllByAcademy(Academy academy) {
        return routeRepository.findAllByAcademy(academy);
    }


}
