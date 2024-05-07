package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;

interface AcademyChildCustomRepository {

    boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily);
}
