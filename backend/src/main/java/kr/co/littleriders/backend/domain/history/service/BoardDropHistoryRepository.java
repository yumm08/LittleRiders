package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BoardDropHistoryRepository extends JpaRepository<BoardDropHistory,Long> {
}
