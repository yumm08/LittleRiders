package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ShuttleDriveServiceImpl implements ShuttleDriveService {
    private final ShuttleDriveRepository shuttleDriveRepository;

    @Override
    public void save(ShuttleDrive shuttleDrive) {
        shuttleDriveRepository.save(shuttleDrive);
    }

}