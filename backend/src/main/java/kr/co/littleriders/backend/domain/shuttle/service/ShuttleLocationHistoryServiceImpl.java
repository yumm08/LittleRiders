package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleLocationHistoryErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationHistoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleLocationHistoryServiceImpl implements ShuttleLocationHistoryService {
    private final ShuttleLocationHistoryRepository shuttleLocationHistoryRepository;

    @Override
    public ShuttleLocationHistory findByShuttleId(long shuttleId) {
        return shuttleLocationHistoryRepository.findById(shuttleId).orElseThrow(
                () -> ShuttleLocationHistoryException.from(ShuttleLocationHistoryErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsByShuttleId(long shuttleId) {
        return shuttleLocationHistoryRepository.existsById(shuttleId);
    }

    @Override
    public List<ShuttleLocationHistory> findAllByShuttleId(long shuttleId) {
        return shuttleLocationHistoryRepository.findAllByShuttleId(shuttleId);
    }

    @Override
    public void save(ShuttleLocationHistory shuttleLocation) {
        shuttleLocationHistoryRepository.save(shuttleLocation);
    }

    @Override
    public void delete(ShuttleLocationHistory shuttleLocation) {
        shuttleLocationHistoryRepository.delete(shuttleLocation);
    }
}