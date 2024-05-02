package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyFamilyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyFamilyException;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AcademyFamilyServiceImpl implements AcademyFamilyService {

    private final AcademyFamilyRepository academyFamilyRepository;

    @Override
    public AcademyFamily findById(long id) {
        return academyFamilyRepository.findById(id).orElseThrow(
                () -> AcademyFamilyException.from(AcademyFamilyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(long id) {
        return academyFamilyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !academyFamilyRepository.existsById(id);
    }

    @Override
    public long save(AcademyFamily academyFamily) {
        return academyFamilyRepository.save(academyFamily).getId();
    }

    @Override
    public boolean existsByFamilyAndAcademy(Family family, Academy academy) {
        return academyFamilyRepository.existsByFamilyAndAcademy(family, academy);
    }

    @Override
    public AcademyFamily findByFamilyAndAcademy(Family family, Academy academy) {
        return academyFamilyRepository.findByFamilyAndAcademy(family, academy).orElseThrow(
                () -> AcademyFamilyException.from(AcademyFamilyErrorCode.NOT_FOUND)
        );
    }

}
