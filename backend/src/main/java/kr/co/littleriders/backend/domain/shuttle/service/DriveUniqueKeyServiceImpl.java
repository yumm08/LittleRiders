package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.DriveUniqueKeyService;
import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class DriveUniqueKeyServiceImpl implements DriveUniqueKeyService {
    private final DriveUniqueKeyRepository driveUniqueKeyRepository;

    @Override
    public void save(DriveUniqueKey driveUniqueKey) {
        driveUniqueKeyRepository.save(driveUniqueKey);
    }

    @Override
    public List<DriveUniqueKey> findByShuttleId(long shuttleId) {
        return driveUniqueKeyRepository.findByShuttleId(shuttleId);
    }

    @Override
    public void delete(DriveUniqueKey driveUniqueKey) {
        driveUniqueKeyRepository.delete(driveUniqueKey);
    }

    @Override
    public void deleteAllByShuttleId(long shuttleId) {
        List<DriveUniqueKey> driveUniqueKeyList = driveUniqueKeyRepository.findByShuttleId(shuttleId);
        driveUniqueKeyRepository.deleteAll(driveUniqueKeyList);
    }


    @Override
    public DriveUniqueKey findByUuid(String uuid) {
        return driveUniqueKeyRepository.findById(uuid).orElseThrow();
    }

    @Override
    public DriveUniqueKey findByAcademyChildId(long academyChildId) {
        return driveUniqueKeyRepository.findByAcademyChildId(academyChildId);
    }


}