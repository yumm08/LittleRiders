package kr.co.littleriders.backend.domain.allowpending.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.allowpending.entity.AcademyChildAllowPending;

@Repository
interface AcademyChildAllowPendingRespository extends JpaRepository<AcademyChildAllowPending, Long> {
}
