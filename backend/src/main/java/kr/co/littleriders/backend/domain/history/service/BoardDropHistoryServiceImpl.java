package kr.co.littleriders.backend.domain.history.service;

import java.util.List;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.BoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class BoardDropHistoryServiceImpl implements BoardDropHistoryService {

    private final BoardDropHistoryRepository boardDropHistoryRepository;

    @Override
    public BoardDropHistory findById(Long id) {
        return boardDropHistoryRepository.findById(id).orElseThrow(
                RuntimeException::new //TODO : 커스텀 익셉션 변경 필요
        );
    }

    @Override
    public boolean existsById(Long id) {
        return boardDropHistoryRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !boardDropHistoryRepository.existsById(id);
    }

    @Override
    public Slice<BoardDropHistory> findByAcademyChild(List<AcademyChild> academyChildList, Pageable pageable) {
        return boardDropHistoryRepository.findByAcademyChild(academyChildList);
    }

    @Override
    public Long save(BoardDropHistory boardDropHistory) {
        return boardDropHistoryRepository.save(boardDropHistory).getId();
    }
}
