package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;

import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface AcademyService {
    Academy findById(long id);

    Academy findByEmail(String email);

    boolean existsById(long id);

    boolean notExistsById(long id);

    boolean existsByEmail(String email);

    boolean notExistsByEmail(String email);

    Slice<Academy> findByName(String name, Pageable pageable);

    long save(Academy academy);

    List<Teacher> findByAcademy(Academy academy);
}
