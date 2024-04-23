package kr.co.littleriders.backend.domain.academychild.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.academychild.entity.AcademyChild;

@Repository
interface AcademyChildRepository extends JpaRepository<AcademyChild, Long> {
}
