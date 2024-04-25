package kr.co.littleriders.backend.domain.family.service;

import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.family.error.code.FamilyErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;

    @Override
    public Family findById(Long id) {
        return familyRepository.findById(id).orElseThrow(
                () -> FamilyException.from(FamilyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public Family findByEmail(String email) {
        return familyRepository.findByEmail(email).orElseThrow(
                () -> FamilyException.from(FamilyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return familyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !familyRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return familyRepository.existsByEmail(email);
    }

    @Override
    public boolean notExistsByEmail(String email) {
        return !familyRepository.existsByEmail(email);
    }

    @Override
    public void save(Family family) {
        familyRepository.save(family);
    }
}
