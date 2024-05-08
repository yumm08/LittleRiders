package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

interface BoardDropHistoryCustomRepository {
    Slice<BoardDropHistory> findByAcademyChild(List<AcademyChildDeprecated> academyChildDeprecatedList, Pageable pageable);
}
