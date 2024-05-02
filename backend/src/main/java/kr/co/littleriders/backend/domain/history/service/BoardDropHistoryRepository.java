package kr.co.littleriders.backend.domain.history.service;

import java.util.List;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BoardDropHistoryRepository extends JpaRepository<BoardDropHistory,Long>, BoardDropHistoryCustomRepository {

}
