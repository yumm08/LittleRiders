package kr.co.littleriders.backend.domain.academy.service;


import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

import java.util.List;

interface AcademyChildCustomRepository {

    List<AcademyChild> findAllByAcademyAndAttending(Academy academy);


    List<AcademyChild> findAllByAcademyAndNotAttending(Academy academy);
}
