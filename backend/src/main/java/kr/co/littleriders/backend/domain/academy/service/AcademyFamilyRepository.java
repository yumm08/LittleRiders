package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.family.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AcademyFamilyRepository extends JpaRepository<AcademyFamily, Long> {

	boolean existsByFamilyAndAcademy(Family family, Academy academy);

	Optional<AcademyFamily> findByFamilyAndAcademy(Family family, Academy academy);
}
