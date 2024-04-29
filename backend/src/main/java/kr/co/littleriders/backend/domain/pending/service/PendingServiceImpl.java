package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.error.code.PendingErrorCode;
import kr.co.littleriders.backend.domain.pending.error.exception.PendingException;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.pending.PendingService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class PendingServiceImpl implements PendingService {

    private final PendingRepository pendingRepository;

    @Override
    public Long save(Pending pending) {
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

        List<Pending> pendingList = pendingRepository.searchById(pendingIdList);

        return pendingList;
    }
}
