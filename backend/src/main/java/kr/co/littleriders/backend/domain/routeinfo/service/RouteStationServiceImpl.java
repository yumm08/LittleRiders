package kr.co.littleriders.backend.domain.routeinfo.service;

import kr.co.littleriders.backend.domain.routeinfo.RouteStationService;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RouteStationServiceImpl implements RouteStationService {
    private final RouteStationRepository routeStationRepository;

    @Override
    public RouteStation findById(Long id) {
        return routeStationRepository.findById(id).orElseThrow(
                RuntimeException::new //TODO : CUSTOM EXCEPTION 으로 수정
        );
    }


    @Override
    public boolean existsById(Long id){
        return routeStationRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id){
        return !routeStationRepository.existsById(id);
    }

}
