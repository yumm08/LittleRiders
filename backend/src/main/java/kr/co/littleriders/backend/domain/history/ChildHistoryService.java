package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;

public interface ChildHistoryService {

    ChildHistory findByCreatedAt(AcademyChildDeprecated academyChildDeprecated);

    ChildHistory findByAcademyChild(AcademyChildDeprecated academyChildDeprecated);

    void save(ChildHistory childHistory);

    ChildHistory findById(Long childHistoryId);
}
