package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

import java.util.Arrays;
import java.util.List;

public interface AcademyChildService {

    AcademyChild findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    void save(AcademyChild academyChild);

    List<AcademyChild> findAllByAcademyAndAttending(Academy academy);

    List<AcademyChild> findAllByAcademyAndNotAttending(Academy academy);
}
