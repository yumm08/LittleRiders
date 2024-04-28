package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class AcademyChildServiceImpl implements AcademyChildService {
    private final AcademyChildRepository academyChildRepository;

    @Override
    public AcademyChild findById(Long id) {
        return academyChildRepository.findById(id).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );
    }

    @Override
    public List<AcademyChild> findAllByAcademyAndAttending(Academy academy) {
        return academyChildRepository.findAllByAcademyAndAttending(academy);
    }

    @Override
    public List<AcademyChild> findAllByAcademyAndNotAttending(Academy academy) {
        return academyChildRepository.findAllByAcademyAndNotAttending(academy);
    }

    @Override
    public boolean existsById(Long id) {
        return academyChildRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !academyChildRepository.existsById(id);
    }

    @Override
    public void save(AcademyChild academyChild) {
        academyChildRepository.save(academyChild);
    }


}
