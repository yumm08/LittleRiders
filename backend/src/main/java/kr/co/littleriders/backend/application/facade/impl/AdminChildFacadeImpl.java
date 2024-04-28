package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AdminChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminChildFacadeImpl implements AdminChildFacade {

    private final PendingService pendingService;
    private final AcademyService academyService;
    private final AcademyFamilyService academyFamilyService;
    private final AcademyChildService academyChildService;

    @Override
    public List<PendingListResponse> readPendingList(Long academyId) {

        Academy academy = academyService.findById(academyId);
        List<PendingListResponse> pendingList = pendingService.findByAcademy(academy).stream()
                                                              .map(PendingListResponse::from)
                                                              .collect(Collectors.toList());

        return pendingList;
    }

    @Override
    public void insertAcademyChildList(Long academyId, List<Long> pendingList) {

        Academy academy = academyService.findById(academyId);
        List<Pending> pendingAllowList = pendingService.findByIdAndAcademy(pendingList, academy);
        pendingAllowList.forEach(this::insertAcademyChild);
    }

    @Override
    public void deletePendingList(Long academyId, List<Long> pendingList) {

        Academy academy = academyService.findById(academyId);
        List<Pending> pendingDenyList = pendingService.findByIdAndAcademy(pendingList, academy);

        pendingDenyList.forEach(pending -> {
            pending.updatePendingStatus(PendingStatus.DENY);
            pendingService.save(pending); // pending status ALLOW 변경
        });
    }

    @Transactional
    public void insertAcademyChild(Pending pending) {

        pending.updatePendingStatus(PendingStatus.ALLOW);
        pendingService.save(pending); // pending status ALLOW 변경

        AcademyFamily academyFamily = AcademyFamily.from(pending);
        academyFamilyService.save(academyFamily); //academyFamily 저장

        AcademyChild academyChild = AcademyChild.of(pending.getChild(), pending.getAcademy(), academyFamily
                , AcademyChildStatus.ATTENDING, CardType.BEACON);
        academyChildService.save(academyChild); //academyChild 저장
    }
}
