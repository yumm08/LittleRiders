package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;

public interface FamilyHistoryService {
	FamilyHistory findByCreatedAt(AcademyFamily academyFamily);
}
