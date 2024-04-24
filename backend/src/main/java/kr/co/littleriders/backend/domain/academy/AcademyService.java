package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface AcademyService {
    Academy findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);
}
