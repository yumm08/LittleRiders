package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;

import java.util.List;

public interface AcademyChildFacade {

    List<PendingListResponse> readPendingList(Long academyId);

    void insertAcademyChildList(Long academyId, List<Long> pendingList);

    void deletePendingList(Long academyId, List<Long> pendingList);

    List<AcademyChildResponse> readAcademyChildList(Long academyId);

    Long updateAcademyChild(Long academyId, Long academyChildId, String status);

    AcademyChildDetailResponse readAcademyChildDetail(Long academyId, Long academyChildId);
}
