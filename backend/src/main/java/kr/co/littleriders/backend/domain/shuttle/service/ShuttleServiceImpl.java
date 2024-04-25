package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ShuttleServiceImpl implements ShuttleService {
    private final ShuttleRepository shuttleRepository;

    @Override
    public Long save(Shuttle shuttle) {
        return shuttleRepository.save(shuttle).getId();
    }
}
