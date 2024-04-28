package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
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
    public Long save(Pending pending) {
        return pendingRespository.save(pending).getId();
    }

    @Override
    public List<Pending> findByChild(List<Child> childList) {
        return pendingRespository.findByChild(childList);
    }

    @Override
    public List<Pending> findByAcademy(Academy academy) {
        return pendingRespository.findByAcademy(academy);
    }
}
