package kr.co.littleriders.backend.domain.academy.service;


import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;

import java.util.List;

interface AcademyChildCustomRepository {

    List<AcademyChild> searchByAcademyAndAttending(Academy academy);

    List<AcademyChild> searchByAcademyAndNotAttending(Academy academy);

    boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);
}
