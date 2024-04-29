package kr.co.littleriders.backend.domain.academy.service;

import java.util.Optional;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamilyStatus;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyFamilyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyFamilyException;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AcademyFamilyServiceImpl implements AcademyFamilyService {

    private final AcademyFamilyRepository academyFamilyRepository;
    @Override
    public AcademyFamily findById(Long id) {
        return academyFamilyRepository.findById(id).orElseThrow(
                () -> AcademyFamilyException.from(AcademyFamilyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return academyFamilyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !academyFamilyRepository.existsById(id);
    }

    @Override
    public void save(AcademyFamily academyFamily) {
        academyFamilyRepository.save(academyFamily);
    }

    @Override
    public AcademyFamily findByFamilyAndAcademy(Family family, Academy academy) {
        return academyFamilyRepository.findByFamilyAndAcademy(family, academy)
            .orElseGet(() -> academyFamilyRepository.save(AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL)));
    }

}
