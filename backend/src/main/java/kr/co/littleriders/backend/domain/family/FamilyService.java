package kr.co.littleriders.backend.domain.family;

import kr.co.littleriders.backend.domain.family.entity.Family;

public interface FamilyService {

    Family findById(Long id);

    Family findByEmail(String email);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    boolean existsByEmail(String email);

    boolean notExistsByEmail(String email);
}
