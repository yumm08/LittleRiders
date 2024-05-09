package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

import java.util.List;

public interface AcademyChildService {

    AcademyChild findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(AcademyChild academyChild);

	List<AcademyChild> findByAcademy(Academy academy);

}
