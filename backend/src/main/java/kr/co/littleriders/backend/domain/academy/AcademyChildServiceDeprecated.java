package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.child.entity.Child;

import java.util.List;

public interface AcademyChildServiceDeprecated {

    AcademyChildDeprecated findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    AcademyChildDeprecated findByCardNumber(String cardNumber);

    long save(AcademyChildDeprecated academyChildDeprecated);

    boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);

    List<AcademyChildDeprecated> findByAcademy(Academy academy);

    List<AcademyChildDeprecated> findByChild(Child child);

    AcademyChildDeprecated findByChildAndAcademy(Child child, Academy academy);

    boolean existsByChildAndAcademy(Child child, Academy academy);
}
