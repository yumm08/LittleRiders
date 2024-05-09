package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;

import java.util.List;

public interface AcademyChildFacade {


    List<AcademyChildResponse> getAcademyChildListByAcademyId(Long academyId);

    Long updateAcademyChild(Long academyId, Long academyChildId, String status);

    AcademyChildDetailResponse getAcademyChildDetail(Long academyId, Long academyChildId);


    //TODO - HOTFIX-이윤지 - 필요없음 (김도현)
//    @Deprecated
//    Map<String, Object> readAcademyChildImage(Long academyId, Long childHistoryId);

    void createAcademyChild(Long academyId, CreateAcademyChildRequest createAcademyChildRequest);
}
