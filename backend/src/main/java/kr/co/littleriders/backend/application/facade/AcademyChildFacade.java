package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;

import java.util.List;

public interface AcademyChildFacade {

    List<AcademyChildResponse> getAcademyChildListByAcademyId(Long academyId);

    Long updateAcademyChildStatus(Long academyId, Long academyChildId, String status);

    Long updateAcademyChild(Long academyId, Long academyChildId );

    AcademyChildDetailResponse getAcademyChildDetail(Long academyId, Long academyChildId);

    Long insertAcademyChild(Long academyId, CreateAcademyChildRequest createAcademyChildRequest);
}
