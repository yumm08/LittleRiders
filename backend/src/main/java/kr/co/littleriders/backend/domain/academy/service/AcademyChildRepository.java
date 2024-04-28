package kr.co.littleriders.backend.domain.academy.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

@Repository
interface AcademyChildRepository extends JpaRepository<AcademyChild, Long>, AcademyChildCustomRepository {
}
