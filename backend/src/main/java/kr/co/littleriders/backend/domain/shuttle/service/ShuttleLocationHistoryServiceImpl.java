package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleLocationHistoryServiceImpl implements ShuttleLocationHistoryService {
    private final ShuttleLocationHistoryRepository shuttleLocationRepository;

    @Override
    public List<ShuttleLocationHistory> findAllByShuttleId(Long shuttleId) {
        return shuttleLocationRepository.findAllByShuttleId(shuttleId);
    }

    @Override
    public void save(ShuttleLocationHistory shuttleLocation) {
        shuttleLocationRepository.save(shuttleLocation);
    }

    @Override
    public void delete(ShuttleLocationHistory shuttleLocation) {
        shuttleLocationRepository.delete(shuttleLocation);
    }
}