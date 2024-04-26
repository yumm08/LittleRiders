package kr.co.littleriders.backend.domain.route.service;

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
    public Route findById(Long id) {
        return routeRepository.findById(id).orElseThrow(() -> RouteException.from(RouteErrorCode.NOT_FOUND));
    }

    @Override
    public boolean existsById(Long id) {
        return routeRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !routeRepository.existsById(id);
    }

    @Override
    public boolean existsByAcademyIdAndName(Long academyId, String name) {
        return routeRepository.existsByAcademyIdAndName(academyId, name);
    }

    @Override
    public void save(Route route) { routeRepository.save(route); }

    @Override
    public List<Route> findAllByAcademyId(Long academyId) {
        return routeRepository.findAllByAcademyId(academyId);
    }

}
