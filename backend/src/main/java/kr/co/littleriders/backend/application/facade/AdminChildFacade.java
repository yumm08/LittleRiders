package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.response.PendingListResponse;

import java.util.List;

public interface AdminChildFacade {

    List<PendingListResponse> readPendingList(Long academyId);
}
