package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardDropHistoryService {

    BoardDropHistory findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    Slice<BoardDropHistory> findByAcademyChild(List<AcademyChildDeprecated> academyChildDeprecatedList, Pageable pageable);

    Long save(BoardDropHistory boardDropHistory);
}
