package kr.co.littleriders.backend.domain.academyfamily.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.academyfamily.entity.AcademyFamily;

@Repository
interface AcademyFamilyRepository extends JpaRepository<AcademyFamily, Long> {
}
