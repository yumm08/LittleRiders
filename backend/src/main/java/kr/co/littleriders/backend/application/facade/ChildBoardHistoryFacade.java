package kr.co.littleriders.backend.application.facade;

import org.springframework.data.domain.Pageable;

import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildDetailHistoryResponse;

public interface ChildBoardHistoryFacade {
	ChildBoardHistoryResponse readChildBoardHistory(Long familyId, Long childId, Pageable pageable);

	ChildDetailHistoryResponse readDetailHistory(Long familyId, Long historyId);
}
