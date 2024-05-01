package kr.co.littleriders.backend.domain.history;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;

public interface BoardDropHistoryService {

    BoardDropHistory findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

	Slice<BoardDropHistory> findByAcademyChild(List<AcademyChild> academyChildList, Pageable pageable);
}
