package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.ShuttleDropService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleDropServiceImpl implements ShuttleDropService {

    private final ShuttleDropRepository shuttleDropRepository;
    @Override
    public ShuttleDrop findByAcademyChildId(long academyChildId) {
        return shuttleDropRepository.findById(academyChildId).orElseThrow();
    }

    @Override
    public List<ShuttleDrop> findByShuttleId(long shuttleId) {
        return shuttleDropRepository.findByShuttleId(shuttleId);
    }

    @Override
    public List<ShuttleDrop> findByAcademyId(long academyId) {
        return shuttleDropRepository.findByAcademyId(academyId);
    }

    @Override
    public void deleteAllByShuttleId(long shuttleId) {
        List<ShuttleDrop> shuttleDropList = shuttleDropRepository.findByShuttleId(shuttleId);
        shuttleDropRepository.deleteAll(shuttleDropList);
    }

    @Override
    public void deleteAllByAcademyId(long academyId) {
        List<ShuttleDrop> shuttleDropList  =shuttleDropRepository.findByAcademyId(academyId);
        shuttleDropRepository.deleteAll(shuttleDropList);
    }

    @Override
    public void delete(ShuttleDrop shuttleDrop) {
        shuttleDropRepository.delete(shuttleDrop);
    }

    @Override
    public void save(ShuttleDrop shuttleDrop) {
        shuttleDropRepository.save(shuttleDrop);
    }
}
