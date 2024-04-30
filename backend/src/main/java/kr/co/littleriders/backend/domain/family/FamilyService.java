package kr.co.littleriders.backend.domain.family;

import kr.co.littleriders.backend.domain.family.entity.Family;

public interface FamilyService {

    Family findById(long id);

    Family findByEmail(String email);

    boolean existsById(long id);

    boolean notExistsById(long id);

    boolean existsByEmail(String email);

    boolean notExistsByEmail(String email);

    long save(Family family);
}
