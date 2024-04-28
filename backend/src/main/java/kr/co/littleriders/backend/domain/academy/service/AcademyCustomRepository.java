package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

interface AcademyCustomRepository {

    Slice<Academy> findByName(@Param("name") String name, Pageable pageable);
}
