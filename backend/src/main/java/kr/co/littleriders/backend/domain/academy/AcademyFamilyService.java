package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.family.entity.Family;

public interface AcademyFamilyService {

    AcademyFamily findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(AcademyFamily academyFamily);

    AcademyFamily findByFamilyAndAcademy(Family family, Academy academy);

}
