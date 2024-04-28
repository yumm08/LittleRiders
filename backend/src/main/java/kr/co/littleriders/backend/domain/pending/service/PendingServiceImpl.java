package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.pending.PendingService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class PendingServiceImpl implements PendingService {

    private final PendingRespository pendingRespository;

    @Override
    public void save(Pending pending) {
        pendingRespository.save(pending);
    }

    @Override
    public List<Pending> findByChildId(List<Child> childList) {

        return pendingRespository.findByChildId(childList);
    }
}
