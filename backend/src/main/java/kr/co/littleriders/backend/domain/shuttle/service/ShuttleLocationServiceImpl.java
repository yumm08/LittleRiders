package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleLocationErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ShuttleLocationServiceImpl implements ShuttleLocationService {
    private final ShuttleLocationRepository shuttleLocationRepository;

    @Override
    public ShuttleLocation findByShuttleId(long shuttleId) {
        return shuttleLocationRepository.findById(shuttleId).orElseThrow(
                () -> ShuttleLocationException.from(ShuttleLocationErrorCode.NOT_FOUND)
        );
    }

    @Override
    public void save(ShuttleLocation shuttleLocation) {
        shuttleLocationRepository.save(shuttleLocation);
    }

    @Override
    public void delete(ShuttleLocation shuttleLocation) {
        shuttleLocationRepository.delete(shuttleLocation);
    }

}