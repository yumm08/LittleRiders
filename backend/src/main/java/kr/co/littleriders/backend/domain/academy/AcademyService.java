package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface AcademyService {
    Academy findById(Long id);

    Academy findByEmail(String email);

    boolean existsById(Long id);

    boolean notExistsById(Long id);


    boolean existsByEmail(String email);

    boolean notExistsByEmail(String email);

    void save(Academy academy);
}
