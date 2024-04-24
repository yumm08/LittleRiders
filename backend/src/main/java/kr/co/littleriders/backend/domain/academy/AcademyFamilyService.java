package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;

public interface AcademyFamilyService {

    AcademyFamily findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);
}
