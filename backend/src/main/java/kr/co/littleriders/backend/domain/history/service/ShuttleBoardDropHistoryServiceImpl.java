package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.ShuttleBoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleBoardDropHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class ShuttleBoardDropHistoryServiceImpl implements ShuttleBoardDropHistoryService {

    private final ShuttleBoardDropHistoryRepository shuttleBoardDropHistoryRepository;

    @Override
    public String save(ShuttleBoardDropHistory shuttleBoardDropHistory) {
        return shuttleBoardDropHistoryRepository.save(shuttleBoardDropHistory).getDocumentId();
    }
}
