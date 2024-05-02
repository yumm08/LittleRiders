package kr.co.littleriders.backend.domain.history.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;

interface BoardDropHistoryCustomRepository {
	Slice<BoardDropHistory> findByAcademyChild(List<AcademyChild> academyChildList, Pageable pageable);
}
