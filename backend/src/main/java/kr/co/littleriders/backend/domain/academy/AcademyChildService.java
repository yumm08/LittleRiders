package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;

import java.util.Collection;
import java.util.List;

public interface AcademyChildService {

    AcademyChild findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    void save(AcademyChild academyChild);

	boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);

	List<AcademyChild> findByAcademy(Academy academy);
}
