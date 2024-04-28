package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public List<Academy> findByName(String name, Pageable pageable) {

        List<Academy> academyList = academyRepository.findByName(name, pageable).getContent();

        return academyList;
    }

}
