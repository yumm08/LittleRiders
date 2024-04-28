package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilyAcademyRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;

import kr.co.littleriders.backend.application.dto.response.AcademyRegistStatusResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FamilyAcademyFacade {

    AcademyListResponse readAcademyList(String name, Pageable pageable);

    Long insertAcademyJoin(Long familyId, FamilyAcademyRegistRequest familyAcademyRegistRequest);

    List<AcademyRegistStatusResponse> readAcademyRegistStatusList(Long familyId);
}
