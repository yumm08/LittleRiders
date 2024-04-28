package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.FamilyAcademyJoinRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;

import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FamilyAcademyFacade {

    List<AcademyListResponse> readAcademyList(String name, Pageable pageable);

    void insertAcademyJoin(AuthFamily authFamily, FamilyAcademyJoinRequest familyAcademyJoinRequest);
}
