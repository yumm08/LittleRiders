package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PendingServiceImpl implements PendingService {

    private final PendingRepository pendingRepository;

    @Override
    public long save(Pending pending) {
        return pendingRepository.save(pending).getId();
    }

    @Override
    public List<Pending> searchByChild(List<Child> childList) {
        return pendingRepository.searchByChild(childList);
    }

    @Override
    public List<Pending> searchByAcademy(Academy academy) {
        return pendingRepository.searchByAcademy(academy);
    }

    @Override
    public List<Pending> searchById(List<Long> pendingIdList) {

        return pendingRepository.searchById(pendingIdList);
    }
}
