package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyChildFacadeImpl implements AcademyChildFacade {

    private final AcademyService academyService;
    private final AcademyChildService academyChildService;
    private final ImageUtil imageUtil;


    //TODO - HOTFIX-이윤지 수정 필요 - 반환값 더 들어가야함
    @Override
    public List<AcademyChildResponse> getAcademyChildListByAcademyId(Long academyId) {

        Academy academy = academyService.findById(academyId);
        return academy.getAcademyChildList()
                .stream()
                .sorted(Comparator.comparing(academyChild -> {
                    return academyChild.getStatus() == AcademyChildStatus.ATTENDING ? 0 : 1;
                }))
                .map(AcademyChildResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public AcademyChildDetailResponse getAcademyChildDetail(Long academyId, Long academyChildId) {
        //TODO - HOTFIX-이윤지 수정 필요 - 학원아이 디테일 정보 반환해야함. ATTENDING 상관 X

//        Academy academy = academyService.findById(academyId);
//        AcademyChild academyChild = academyChildService.findById(academyChildId);
//        if (!academyChild.equalsAcademy(academy)) {
//            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
//        }
//
//        AcademyChildDetailResponse childDetail;
//        ChildHistory childHistory = childHistoryService.findByCreatedAt(academyChild);
//        if (academyChild.isFamilyAvail()) {
//            childDetail = AcademyChildDetailResponse.of(childHistory, null, academyChild);
//        } else {
//            FamilyHistory familyHistory = familyHistoryService.findByCreatedAt(academyChild.getAcademyFamily());
//            childDetail = AcademyChildDetailResponse.of(childHistory, familyHistory, academyChild);
//        }

        return null;
    }


    //TODO - HOTFIX-이윤지 수정 필요 - 사실 필요없음 이거 (김도현)
//    @Deprecated
//    @Override
//    public Map<String, Object> readAcademyChildImage(Long academyId, Long childHistoryId) {
//
//        Academy academy = academyService.findById(academyId);
//        ChildHistory childHistory = childHistoryService.findById(childHistoryId);
//        Child child = childHistory.getChild();
//        AcademyChild academyChild = academyChildService.findByChildAndAcademy(child, academy);
//        if (childHistory.isBeforeUpdatedAt(academyChild)) {
//            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
//        }
//
//        String imagePath = childHistory.getImagePath();
//        Map<String, Object> result = imageUtil.getImage(imagePath);
//
//        return result;
//    }


    //TODO - HOTFIX-이윤지 수정 필요 - 학원 아이 등록
    @Override
    public void createAcademyChild(Long academyId, CreateAcademyChildRequest createAcademyChildRequest) {

    }


    //TODO - HOTFIX-이윤지 수정 필요 - status 만 업데이트 할게 아닌 다른 정보도 업데이트 해야함 Attending 상관없음 (김도현)
    @Override
    public Long updateAcademyChild(Long academyId, Long academyChildId, String status) {

        Academy academy = academyService.findById(academyId);

        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
        }

        //여기에 로직 추가
        academyChildService.save(academyChild);

        return academyChild.getId();
    }



}
