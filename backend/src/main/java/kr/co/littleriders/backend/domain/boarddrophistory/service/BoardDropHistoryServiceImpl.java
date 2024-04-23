package kr.co.littleriders.backend.domain.boarddrophistory.service;

import kr.co.littleriders.backend.domain.boarddrophistory.BoardDropHistoryService;
import kr.co.littleriders.backend.domain.boarddrophistory.entity.BoardDropHistory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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
}
