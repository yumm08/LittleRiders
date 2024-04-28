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
    public List<Pending> findByChild(List<Child> childList) {
        return pendingRepository.findByChild(childList);
    }

    @Override
    public List<Pending> findByAcademy(Academy academy) {
        return pendingRepository.findByAcademy(academy);
    }

    @Override
    public List<Pending> findByIdAndAcademy(List<Long> pendingList, Academy academy) {

        List<Pending> pendingAllowList = pendingRepository.findByIdList(pendingList);

        if (pendingAllowList.stream().anyMatch(pending -> !pending.getAcademy().equals(academy))) {
            throw PendingException.from(PendingErrorCode.ILLEGAL_ACADEMY);
        }

        return pendingAllowList;
    }
}
