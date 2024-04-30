package kr.co.littleriders.backend.application.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.response.ChildBoardHistory;
import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildDetailHistoryResponse;
import kr.co.littleriders.backend.application.facade.ChildBoardHistoryFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.exception.ChildException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.history.BoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import kr.co.littleriders.backend.domain.history.error.code.BoardDropHistoryErrorCode;
import kr.co.littleriders.backend.domain.history.error.exception.BoardDropHistoryException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ChildBoardHistoryFacadeImpl implements ChildBoardHistoryFacade {

	private final BoardDropHistoryService boardDropHistoryService;
	private final AcademyChildService academyChildService;
	private final FamilyService familyService;
	private final ChildService childService;

	@Override
	public ChildBoardHistoryResponse readChildBoardHistory(Long familyId, Long childId, Pageable pageable) {

		Family family = familyService.findById(familyId);
		Child child = childService.findById(childId);
		if (!child.equalsFamily(family)) {
			throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
		}

		List<AcademyChild> academyChildList = academyChildService.findByChild(child);
		Slice<BoardDropHistory> boardHistoryPage = boardDropHistoryService.findByAcademyChild(academyChildList, pageable);

		List<ChildBoardHistory> boardHistoryList = boardHistoryPage.getContent()
																    .stream().map(ChildBoardHistory::from)
																	.collect(Collectors.toList());

		return ChildBoardHistoryResponse.of(boardHistoryList, boardHistoryPage.getNumber(), boardHistoryPage.hasNext());

	}

	@Override
	public ChildDetailHistoryResponse readDetailHistory(Long familyId, Long historyId) {

		Family family = familyService.findById(familyId);

		// detail 조회
		BoardDropHistory boardDropHistory = boardDropHistoryService.findById(historyId);

		// family 접근 권한 확인
		if (!boardDropHistory.equalsFamily(family)) {
			throw BoardDropHistoryException.from(BoardDropHistoryErrorCode.ILLEGAL_ACCESS); // 추후 에러 수정 예정
		}

		ChildDetailHistoryResponse childDetailHistoryResponse = ChildDetailHistoryResponse.from(boardDropHistory);

		return childDetailHistoryResponse;
	}

}
