package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.ChildBoardHistory;
import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildDetailHistoryResponse;
import kr.co.littleriders.backend.application.facade.ChildBoardHistoryFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildServiceDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ChildBoardHistoryFacadeImpl implements ChildBoardHistoryFacade {

    private final BoardDropHistoryService boardDropHistoryService;
    private final AcademyChildServiceDeprecated academyChildServiceDeprecated;
    private final FamilyService familyService;
    private final ChildService childService;


    @Deprecated
    @Override
    public ChildBoardHistoryResponse readChildBoardHistory(Long familyId, Long childId, Pageable pageable) {

        Family family = familyService.findById(familyId);
        Child child = childService.findById(childId);
        if (!child.equalsFamily(family)) {
            throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
        }

        List<AcademyChildDeprecated> academyChildDeprecatedList = academyChildServiceDeprecated.findByChild(child);
        Slice<BoardDropHistory> boardHistoryPage = boardDropHistoryService.findByAcademyChild(academyChildDeprecatedList, pageable);

        List<ChildBoardHistory> boardHistoryList = boardHistoryPage.getContent()
                .stream().map(ChildBoardHistory::from)
                .collect(Collectors.toList());

        return ChildBoardHistoryResponse.of(boardHistoryList, boardHistoryPage.getNumber(), boardHistoryPage.hasNext());

    }


    @Deprecated
    @Override
    public ChildDetailHistoryResponse readDetailHistory(Long familyId, Long historyId) {

        Family family = familyService.findById(familyId);

        BoardDropHistory boardDropHistory = boardDropHistoryService.findById(historyId);

        if (!boardDropHistory.equalsFamily(family)) {
            throw BoardDropHistoryException.from(BoardDropHistoryErrorCode.ILLEGAL_ACCESS);
        }

        ChildDetailHistoryResponse childDetailHistoryResponse = ChildDetailHistoryResponse.from(boardDropHistory);

        return childDetailHistoryResponse;
    }

}
