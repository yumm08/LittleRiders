package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.pending.entity.Pending;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.pending.PendingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class PendingServiceImpl implements PendingService {

    private final PendingRespository pendingRespository;

    @Override
    public void save(Pending pending) {
        pendingRespository.save(pending);
    }
}
