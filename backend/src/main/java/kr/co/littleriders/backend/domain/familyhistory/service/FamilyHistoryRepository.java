package kr.co.littleriders.backend.domain.familyhistory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.familyhistory.entity.FamilyHistory;

@Repository
interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {
}
