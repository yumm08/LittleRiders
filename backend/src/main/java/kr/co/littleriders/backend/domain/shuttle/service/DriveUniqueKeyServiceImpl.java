package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.DriveUniqueKeyService;
import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DriveUniqueKeyServiceImpl implements DriveUniqueKeyService {
    private final DriveUniqueKeyRepository driveUniqueKeyRepository;

    @Override
    public void save(DriveUniqueKey driveUniqueKey) {
        driveUniqueKeyRepository.save(driveUniqueKey);
    }

}