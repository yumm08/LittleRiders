package kr.co.littleriders.backend.domain.academy;

import java.util.Optional;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.family.entity.Family;

public interface AcademyFamilyService {

    AcademyFamily findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    void save(AcademyFamily academyFamily);

    AcademyFamily findByFamilyAndAcademy(Family family, Academy academy);

}
