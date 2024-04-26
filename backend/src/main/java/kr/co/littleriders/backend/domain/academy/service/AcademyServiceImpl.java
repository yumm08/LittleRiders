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
    public Academy findById(final Long id) {
        return academyRepository.findById(id).orElseThrow(
                () -> AcademyException.from(AcademyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public Academy findByEmail(final String email) {
        return academyRepository.findByEmail(email).orElseThrow(
                () -> AcademyException.from(AcademyErrorCode.NOT_FOUND)
        );
    }


    @Override
    public boolean existsById(final Long id) {
        return academyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final Long id) {
        return !academyRepository.existsById(id);
    }


    @Override
    public boolean existsByEmail(final String email) {
        return academyRepository.existsByEmail(email);
    }

    @Override
    public boolean notExistsByEmail(String email) {
        return !academyRepository.existsByEmail(email);
    }

    @Override
    public void save(Academy academy) {
        academyRepository.save(academy);
    }

}
