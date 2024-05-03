package kr.co.littleriders.backend.domain.history.service;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;

@Repository
interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {

	FamilyHistory findByFamilyAndCreatedAtBeforeOrderByCreatedAtDesc(Family family, LocalDateTime updatedAt);
}
