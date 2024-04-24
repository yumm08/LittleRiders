package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class AcademyServiceImpl implements AcademyService {
    private final AcademyRepository academyRepository;

    @Override
    public Academy findById(Long id) {
        return academyRepository.findById(id).orElseThrow(
                () -> AcademyException.from(AcademyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return academyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !academyRepository.existsById(id);
    }


}
