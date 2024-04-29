package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AdminChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import kr.co.littleriders.backend.domain.pending.error.code.PendingErrorCode;
import kr.co.littleriders.backend.domain.pending.error.exception.PendingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminChildFacadeImpl implements AdminChildFacade {

    private final PendingService pendingService;
    private final AcademyService academyService;
    private final AcademyFamilyService academyFamilyService;
    private final AcademyChildService academyChildService;
    private final ChildHistoryService childHistoryService;

    @Override
    public List<AcademyChildResponse> readAcademyChildList(Long academyId) {

        Academy academy = academyService.findById(academyId);
        List<AcademyChildResponse> attendingChild = academyChildService.searchByAcademyAndAttending(academy)
                                                                        .stream()
                                                                        .map(AcademyChildResponse::from)
                                                                        .collect(Collectors.toList());

        List<AcademyChildResponse> notAttendingChild = academyChildService.searchByAcademyAndNotAttending(academy)
                .stream().map(academyChild -> {
                    ChildHistory childHistory = childHistoryService.findByCreatedAt(academyChild);
                    AcademyChildResponse childResponse = AcademyChildResponse.of(academyChild, childHistory);

                    return childResponse;
                }).collect(Collectors.toList());

        List<AcademyChildResponse> academyChildList = new ArrayList<>();
        academyChildList.addAll(attendingChild);
        academyChildList.addAll(notAttendingChild);
        return academyChildList;
    }

    @Override
    public Long updateAcademyChild(Long academyId, Long academyChildId, String status) {

        Academy academy = academyService.findById(academyId);

        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND); // 추후 다른 에러 메시지로 변경
        }

        // 원생 정보 update 후 저장
        academyChild.updateStatus(AcademyChildStatus.valueOf(status.toUpperCase()));
        academyChildService.save(academyChild);

        // 원생 보호자 정보 update 후 저장 => 학원에 남아있는 자녀 있.없 확인
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
        pendingService.save(pending); // pending status ALLOW 변경

        // academyFamily 존재하는 지 확인 후 없으면 만들어서 반환
        AcademyFamily academyFamily = academyFamilyService.findByFamilyAndAcademy(pending.getChild().getFamily(), academy);

        AcademyChild academyChild = AcademyChild.of(pending.getChild()
                                                    , pending.getAcademy()
                                                     , academyFamily
                                                    , AcademyChildStatus.ATTENDING, CardType.BEACON);
        academyChildService.save(academyChild); //academyChild 저장
    }

    @Transactional
    public void deletePending(Pending pending, Academy academy) {

        if (!pending.getAcademy().equals(academy)) {
            throw PendingException.from(PendingErrorCode.ILLEGAL_ACADEMY);
        }

        pending.updatePendingStatus(PendingStatus.DENY);
        pendingService.save(pending); // pending status DENY 변경
    }
}
