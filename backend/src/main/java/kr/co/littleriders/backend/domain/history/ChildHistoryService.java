package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;

public interface ChildHistoryService {

    ChildHistory findByCreatedAt(AcademyChild academyChild);

    ChildHistory findByAcademyChild(AcademyChild academyChild);

    void save(ChildHistory childHistory);

    ChildHistory findById(Long childHistoryId);
}
