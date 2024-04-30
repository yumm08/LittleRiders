package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ShuttleLocationServiceImpl implements ShuttleLocationService {
    private final ShuttleLocationRepository shuttleLocationRepository;

    @Override
    public void save(ShuttleLocation shuttleLocation) {
        shuttleLocationRepository.save(shuttleLocation);
    }

}