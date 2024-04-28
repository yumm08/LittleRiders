package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.FamilyAcademyJoinRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;
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
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import lombok.RequiredArgsConstructor;
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
    public List<AcademyListResponse> readAcademyList(String name, Pageable pageable) {

        List<AcademyListResponse> academyList = academyService.findByName(name, pageable)
                                                              .stream()
                                                              .map(AcademyListResponse::from)
                                                              .collect(Collectors.toList());

        return academyList;
    }

    @Override
    public void insertAcademyJoin(AuthFamily authFamily, FamilyAcademyJoinRequest familyAcademyJoinRequest) {

        Academy academy = academyService.findById(familyAcademyJoinRequest.getAcademyId());
        Child child = childService.findById(familyAcademyJoinRequest.getChildId());
        Family family = familyService.findById(authFamily.getId());

        if (!family.getChild().contains(child)) {
            throw FamilyChildException.from(FamilyChildErrorCode.NOT_FOUND);
        }

        Pending pending = Pending.of(academy, child);
        pendingService.save(pending);
    }
}
