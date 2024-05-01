package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.history.error.code.ShuttleDriveHistoryErrorCode;
import kr.co.littleriders.backend.domain.history.error.exception.ShuttleDriveHistoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ShuttleDriveHistoryServiceImpl implements ShuttleDriveHistoryService {
    private final ShuttleDriveHistoryRepository shuttleDriveHistoryRepository;

    @Override
    public ShuttleDriveHistory findById(String id) {
        return shuttleDriveHistoryRepository.findById(id).orElseThrow(
                () -> ShuttleDriveHistoryException.from(ShuttleDriveHistoryErrorCode.NOT_FOUND)
        );
    }

    @Override
    public String save(ShuttleDriveHistory shuttleDriveHistory) {
        return shuttleDriveHistoryRepository.save(shuttleDriveHistory).getDocumentId();
    }
}
