package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AcademyChildRepository extends JpaRepository<AcademyChild, Long> {
	List<AcademyChild> findByAcademy(Academy academy);

    Optional<AcademyChild> findByBeaconNumber(String beaconNumber);

}
