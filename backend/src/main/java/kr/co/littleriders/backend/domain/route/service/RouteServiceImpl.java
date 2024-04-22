package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.domain.route.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;


}
