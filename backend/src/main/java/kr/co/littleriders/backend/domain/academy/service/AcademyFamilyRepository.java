package kr.co.littleriders.backend.domain.academy.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.family.entity.Family;

@Repository
interface AcademyFamilyRepository extends JpaRepository<AcademyFamily, Long> {
	Optional<AcademyFamily> findByFamilyAndAcademy(Family family, Academy academy);
}
