package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRide;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleChildRideErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleChildRideException;
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

    @Override
    public ShuttleChildRide findByShuttleId(long shuttleId) {
        return shuttleChildRideRepository.findByShuttleId(shuttleId).orElseThrow(
                () -> ShuttleChildRideException.from(ShuttleChildRideErrorCode.NOT_FOUND)
        );

    }

    @Override
    public void delete(ShuttleChildRide shuttleChildRide) {
        shuttleChildRideRepository.delete(shuttleChildRide);
    }

}