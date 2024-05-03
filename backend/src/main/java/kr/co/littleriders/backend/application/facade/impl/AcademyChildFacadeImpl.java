package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import kr.co.littleriders.backend.domain.history.FamilyHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import kr.co.littleriders.backend.domain.pending.error.code.PendingErrorCode;
import kr.co.littleriders.backend.domain.pending.error.exception.PendingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyChildFacadeImpl implements AcademyChildFacade {

    private final PendingService pendingService;
    private final AcademyService academyService;
    private final AcademyFamilyService academyFamilyService;
    private final AcademyChildService academyChildService;
    private final ChildHistoryService childHistoryService;
    private final FamilyHistoryService familyHistoryService;

    @Override
    public List<AcademyChildResponse> readAcademyChildList(Long academyId) {

        Academy academy = academyService.findById(academyId);

        List<AcademyChildResponse> academyChildList = academyChildService.findByAcademy(academy)
                                                                         .stream()
                                                                         .sorted(Comparator.comparing(child -> {
                                                                             return child.getStatus() == AcademyChildStatus.ATTENDING ? 0 : 1;
                                                                         }))
                                                                         .map(AcademyChildResponse::from)
                                                                         .collect(Collectors.toList());

        return academyChildList;
    }

    @Override
    public AcademyChildDetailResponse readAcademyChildDetail(Long academyId, Long academyChildId) {

        Academy academy = academyService.findById(academyId);
        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
        }

        AcademyChildDetailResponse childDetail;
        if (academyChild.isAttending()) {
            childDetail = AcademyChildDetailResponse.from(academyChild);
        } else if (academyChild.isFamilyAvail()) {
            ChildHistory childHistory = childHistoryService.findByCreatedAt(academyChild);
            childDetail = AcademyChildDetailResponse.to(childHistory, null, academyChild);
        } else {
            ChildHistory childHistory = childHistoryService.findByCreatedAt(academyChild);
            FamilyHistory familyHistory = familyHistoryService.findByCreatedAt(academyChild.getAcademyFamily());
            childDetail = AcademyChildDetailResponse.to(childHistory, familyHistory, academyChild);
        }

        return childDetail;
    }

    @Override
    public Long updateAcademyChild(Long academyId, Long academyChildId, String status) {

        Academy academy = academyService.findById(academyId);

        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND); // TODO-이윤지-에러 메시지 변경
        }

        // TODO-이윤지-attending->graduate/leave 만 가능하도록 제약
        academyChild.updateStatus(AcademyChildStatus.valueOf(status.toUpperCase()));
        academyChildService.save(academyChild);

        AcademyFamily academyFamily = academyChild.getAcademyFamily();
        if (!academyChildService.existsByAcademyFamilyAndAttending(academyFamily)) {
            academyFamily.updateStatus(AcademyFamilyStatus.NOT_AVAIL);
            academyFamilyService.save(academyFamily);
        }

        return academyChild.getId();
    }


    @Override
    public List<PendingListResponse> readPendingList(Long academyId) {

        Academy academy = academyService.findById(academyId);
        List<PendingListResponse> pendingList = pendingService.searchByAcademy(academy).stream()
                                                              .map(PendingListResponse::from)
                                                              .collect(Collectors.toList());

        return pendingList;
    }

    @Override
    public void insertAcademyChildList(Long academyId, List<Long> pendingList) {

        Academy academy = academyService.findById(academyId);
        List<Pending> pendingAllowList = pendingService.searchById(pendingList);

        pendingAllowList.forEach(pending -> {
            insertAcademyChild(pending, academy);
        });
    }

    @Override
    public void deletePendingList(Long academyId, List<Long> pendingList) {

        Academy academy = academyService.findById(academyId);
        List<Pending> pendingDenyList = pendingService.searchById(pendingList);

        pendingDenyList.forEach(pending -> {
            deletePending(pending, academy);
        });
    }

    @Transactional
    public void insertAcademyChild(Pending pending, Academy academy) {

        if (!pending.getAcademy().equals(academy)) {
            throw PendingException.from(PendingErrorCode.ILLEGAL_ACADEMY);
        }

        pending.updatePendingStatus(PendingStatus.ALLOW);
        pendingService.save(pending);

        AcademyFamily academyFamily;
        if(academyFamilyService.existsByFamilyAndAcademy(pending.getChild().getFamily(), academy)) {
            academyFamily = AcademyFamily.of(pending.getChild().getFamily(), academy, AcademyFamilyStatus.AVAIL);
            academyFamilyService.save(academyFamily);
        } else {
            academyFamily = academyFamilyService.findByFamilyAndAcademy(pending.getChild().getFamily(), academy);
        }

        // TODO-이윤지-전에 다녔던 학생인지 확인 후 insert 필수
        AcademyChild academyChild = AcademyChild.of(pending.getChild()
                                                    , pending.getAcademy()
                                                     , academyFamily
                                                    , AcademyChildStatus.ATTENDING, CardType.BEACON);
        academyChildService.save(academyChild);
    }

    @Transactional
    public void deletePending(Pending pending, Academy academy) {

        if (!pending.getAcademy().equals(academy)) {
            throw PendingException.from(PendingErrorCode.ILLEGAL_ACADEMY);
        }

        pending.updatePendingStatus(PendingStatus.DENY);
        pendingService.save(pending);
    }
}
