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
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import kr.co.littleriders.backend.domain.history.FamilyHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import kr.co.littleriders.backend.domain.pending.error.code.PendingErrorCode;
import kr.co.littleriders.backend.domain.pending.error.exception.PendingException;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    private final ImageUtil imageUtil;

    @Override
    public List<AcademyChildResponse> readAcademyChildList(Long academyId) {

        Academy academy = academyService.findById(academyId);

        List<AcademyChildResponse> academyChildList = academyChildService.findByAcademy(academy)
                                                     .stream()
                                                     .sorted(Comparator.comparing(child -> {
                                                         return child.getStatus() == AcademyChildStatus.ATTENDING ? 0 : 1;
                                                     }))
                                                     .map(academyChild -> {
                                                         ChildHistory childHistory = childHistoryService.findByAcademyChild(academyChild);
                                                         AcademyChildResponse childResponse = AcademyChildResponse.of(academyChild, childHistory);

                                                         return childResponse;
                                                     })
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
        ChildHistory childHistory = childHistoryService.findByCreatedAt(academyChild);
        if (academyChild.isFamilyAvail()) {
            childDetail = AcademyChildDetailResponse.of(childHistory, null, academyChild);
        } else {
            FamilyHistory familyHistory = familyHistoryService.findByCreatedAt(academyChild.getAcademyFamily());
            childDetail = AcademyChildDetailResponse.of(childHistory, familyHistory, academyChild);
        }

        return childDetail;
    }

    @Override
    public Map<String, Object> readAcademyChildImage(Long academyId, Long childHistoryId) {

        Academy academy = academyService.findById(academyId);
        ChildHistory childHistory = childHistoryService.findById(childHistoryId);
        Child child = childHistory.getChild();
        AcademyChild academyChild = academyChildService.findByChildAndAcademy(child, academy);
        if (childHistory.isBeforeUpdatedAt(academyChild)) {
            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
        }

        String imagePath = childHistory.getImagePath();
        Map<String, Object> result = imageUtil.getImage(imagePath);

        return result;
    }

    @Override
    public Long updateAcademyChild(Long academyId, Long academyChildId, String status) {

        Academy academy = academyService.findById(academyId);

        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
        }

        if (!academyChild.getStatus().equals(AcademyChildStatus.ATTENDING)) {
            throw AcademyChildException.from(AcademyChildErrorCode.FORBIDDEN);
        }

        AcademyChildStatus updateStatus = AcademyChildStatus.valueOf(status.toUpperCase());
        if (updateStatus.equals(AcademyChildStatus.ATTENDING)) {
            throw AcademyChildException.from(AcademyChildErrorCode.FORBIDDEN);
        }
        academyChild.updateStatus(updateStatus);
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
        if(!academyFamilyService.existsByFamilyAndAcademy(pending.getChild().getFamily(), academy)) {
            academyFamily = AcademyFamily.of(pending.getChild().getFamily(), academy, AcademyFamilyStatus.AVAIL);
        } else {
            academyFamily = academyFamilyService.findByFamilyAndAcademy(pending.getChild().getFamily(), academy);
            academyFamily.updateStatus(AcademyFamilyStatus.AVAIL);
        }
        academyFamilyService.save(academyFamily);

        AcademyChild academyChild;
        if(!academyChildService.existsByChildAndAcademy(pending.getChild(), academy)) {
            academyChild = AcademyChild.of(pending.getChild(),
                                            pending.getAcademy(),
                                            academyFamily,
                                            AcademyChildStatus.ATTENDING,
                                            CardType.BEACON);
        } else {
            academyChild = academyChildService.findByChildAndAcademy(pending.getChild(), academy);
            academyChild.updateStatus(AcademyChildStatus.ATTENDING);
        }

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
