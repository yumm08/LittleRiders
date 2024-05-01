package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ShuttleChildRideServiceImpl implements ShuttleChildRideService {
    private final ShuttleChildRideRepository shuttleChildRideRepository;

    @Override
    public void save(ShuttleChildRide shuttleChildRide) {
        shuttleChildRideRepository.save(shuttleChildRide);
    }

}