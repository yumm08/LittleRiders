package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.FamilyAcademyRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyRegistStatusResponse;
import kr.co.littleriders.backend.application.facade.FamilyAcademyFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.family.error.code.FamilyChildErrorCode;
import kr.co.littleriders.backend.domain.family.error.exception.FamilyChildException;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class FamilyAcademyFacadeImpl implements FamilyAcademyFacade {

    private final AcademyService academyService;
    private final ChildService childService;
    private final FamilyService familyService;
    private final PendingService pendingService;

    @Override
    public AcademyListResponse readAcademyList(String name, Pageable pageable) {

        Page<Academy> academyPage = academyService.findByName(name, pageable);

        List<AcademyList> academyList = academyPage.getContent()
                                                   .stream()
                                                   .map(AcademyList::from)
                                                   .collect(Collectors.toList());


        return AcademyListResponse.of(academyList, academyPage.getNumber(), academyPage.isLast());
    }

    @Override
    public void insertAcademyJoin(AuthFamily authFamily, FamilyAcademyRegistRequest familyAcademyRegistRequest) {

        Academy academy = academyService.findById(familyAcademyRegistRequest.getAcademyId());
        Child child = childService.findById(familyAcademyRegistRequest.getChildId());
        Family family = familyService.findById(authFamily.getId());

        if (!family.getChild().contains(child)) {
            throw FamilyChildException.from(FamilyChildErrorCode.NOT_FOUND);
        }

        Pending pending = Pending.of(academy, child, PendingStatus.PENDING);
        pendingService.save(pending);
    }

    @Override
    public List<AcademyRegistStatusResponse> readAcademyRegistStatusList(AuthFamily authFamily) {

        List<Child> childList = familyService.findById(authFamily.getId()).getChild();
        List<AcademyRegistStatusResponse> academyList = pendingService.findByChildId(childList)
                                                                      .stream()
                                                                      .map(AcademyRegistStatusResponse::from)
                                                                      .collect(Collectors.toList());

        return academyList;
    }
}
