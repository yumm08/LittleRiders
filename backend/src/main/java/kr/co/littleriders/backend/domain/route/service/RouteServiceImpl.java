package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id).orElseThrow(
                RuntimeException::new //TODO : 커스텀 익셉션 변경 필요
        );
    }

    @Override
    public boolean existsById(Long id) {
        return routeRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !routeRepository.existsById(id);
    }


}
