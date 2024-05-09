package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleLocationServiceImpl implements ShuttleLocationService {
    private final ShuttleLocationRepository shuttleLocationRepository;

    @Override
    public List<ShuttleLocation> findByShuttleId(long shuttleId) {

        return shuttleLocationRepository.findByShuttleId(shuttleId);
    }

    @Override
    public void save(ShuttleLocation shuttleLocation) {
        shuttleLocationRepository.save(shuttleLocation);
    }

    @Override
    public void delete(ShuttleLocation shuttleLocation) {
        shuttleLocationRepository.delete(shuttleLocation);
    }

    @Override
    public void deleteAllByShuttleId(long shuttleId) {
        List<ShuttleLocation> shuttleLocationList = findByShuttleId(shuttleId);
        shuttleLocationRepository.deleteAll(shuttleLocationList);
    }

}