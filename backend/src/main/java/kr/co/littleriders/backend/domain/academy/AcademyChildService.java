package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.child.entity.Child;

import java.util.List;

public interface AcademyChildService {

    AcademyChild findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    AcademyChild findByCardNumber(String cardNumber);
    long save(AcademyChild academyChild);

	boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);

	List<AcademyChild> findByAcademy(Academy academy);

	List<AcademyChild> findByChild(Child child);
}
