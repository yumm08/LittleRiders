package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.history.error.code.ChildHistoryErrorCode;
import kr.co.littleriders.backend.domain.history.error.exception.ChildHistoryException;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ChildHistoryServiceImpl implements ChildHistoryService {

    private final ChildHistoryRepository childHistoryRepository;

    @Override
    public ChildHistory findByCreatedAt(AcademyChild academyChild) {
        return childHistoryRepository.findByCreatedAt(academyChild.getChild(), academyChild.getUpdatedAt());
    }

    @Override
    public ChildHistory findByAcademyChild(AcademyChild academyChild) {
        if (academyChild.getStatus().equals(AcademyChildStatus.ATTENDING))
            return childHistoryRepository.findLatestByChild(academyChild.getChild());
        return childHistoryRepository.findByCreatedAt(academyChild.getChild(), academyChild.getUpdatedAt());
    }

    @Override
    public void save(ChildHistory childHistory) {
        childHistoryRepository.save(childHistory);
    }

    @Override
    public ChildHistory findById(Long childHistoryId) {
        return childHistoryRepository.findById(childHistoryId)
                .orElseThrow(() -> ChildHistoryException.from(ChildHistoryErrorCode.NOT_FOUND));
    }

}
