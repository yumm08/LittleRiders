package kr.co.littleriders.backend.domain.childhistory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.childhistory.entity.ChildHistory;

@Repository
interface ChildHistoryRepository extends JpaRepository<ChildHistory, Long> {
}
