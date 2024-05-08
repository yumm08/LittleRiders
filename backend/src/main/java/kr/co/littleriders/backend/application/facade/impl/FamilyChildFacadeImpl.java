package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildServiceDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.exception.ChildException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.history.ChildHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class FamilyChildFacadeImpl implements FamilyChildFacade {

    private final ChildService childService;
    private final FamilyService familyService;
    private final AcademyChildServiceDeprecated academyChildServiceDeprecated;
    private final ChildHistoryService childHistoryService;
    private final ImageUtil imageUtil;

    @Override
    public Long insertChild(ChildRegistRequest childRegistRequest, Long familyId) {

        Family family = familyService.findById(familyId);
        Child child = childRegistRequest.toEntity(family);

        MultipartFile image = childRegistRequest.getImage();
        if (image != null) {
            String imagePath = imageUtil.saveImage(image);
            child.setImagePath(imagePath);
        }

        Long childId = childService.save(child);
        ChildHistory childHistory = ChildHistory.from(child);
        childHistoryService.save(childHistory);

        return childId;
    }

    @Override
    public List<ChildListResponse> readChildList(Long familyId) {

        Family family = familyService.findById(familyId);

        List<ChildListResponse> childList = childService.findByFamilyId(family.getId())
                .stream()
                .map(ChildListResponse::from)
                .collect(Collectors.toList());

        return childList;
    }


    @Deprecated
    @Override
    public ChildDetailResponse readChildDetail(Long familyId, Long childId) {

        Family family = familyService.findById(familyId);

        Child child = childService.findById(childId);
        if (!child.equalsFamily(family)) {
            throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
        }

        // TODO-이윤지-자녀 상태 가져오기 (승차중인지 아닌지)-안해도됌
        /**
         * 1. 자녀가 타야하는 노선 목록 가져오기
         * 2. redis에서 해당 노선이 운행중임을 확인 -> RUNNING
         * 3. 자녀가 탔으면 -> BOARDING
         * 4. 전부 다 아니면 -> NONE
         */
        String status = null;

        List<AcademyList> academyList = academyChildServiceDeprecated.findByChild(child)
                .stream()
                .map(AcademyChildDeprecated::getAcademy)
                .map(AcademyList::from)
                .collect(Collectors.toList());

        ChildDetailResponse childDetail = ChildDetailResponse.of(child, status, academyList);

        return childDetail;
    }

    @Override
    public Map<String, Object> readChildImage(Long familyId, Long childId) {

        Family family = familyService.findById(familyId);
        Child child = childService.findById(childId);
        if (!child.equalsFamily(family)) {
            throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
        }

        String imagePath = child.getImagePath();
        Map<String, Object> result = imageUtil.getImage(imagePath);

        return result;
    }
}
