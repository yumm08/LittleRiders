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
}
