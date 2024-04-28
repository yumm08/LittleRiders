package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
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
}
