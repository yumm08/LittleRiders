package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.history.error.code.ChildHistoryErrorCode;
import kr.co.littleriders.backend.domain.history.error.exception.ChildHistoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ChildHistoryServiceImpl implements ChildHistoryService {

    private final ChildHistoryRepository childHistoryRepository;

    @Override
    public ChildHistory findByCreatedAt(AcademyChildDeprecated academyChildDeprecated) {
        return childHistoryRepository.findByCreatedAt(academyChildDeprecated.getChild(), academyChildDeprecated.getUpdatedAt());
    }

    @Override
    public ChildHistory findByAcademyChild(AcademyChildDeprecated academyChildDeprecated) {
        if (academyChildDeprecated.getStatus().equals(AcademyChildStatus.ATTENDING))
            return childHistoryRepository.findLatestByChild(academyChildDeprecated.getChild());
        return childHistoryRepository.findByCreatedAt(academyChildDeprecated.getChild(), academyChildDeprecated.getUpdatedAt());
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
