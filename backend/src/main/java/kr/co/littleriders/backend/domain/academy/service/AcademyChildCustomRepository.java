package kr.co.littleriders.backend.domain.academy.service;


import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;

import java.util.List;

interface AcademyChildCustomRepository {

    boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);
}
