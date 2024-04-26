package kr.co.littleriders.backend.application.facade;

import java.util.List;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.domain.family.entity.Family;

public interface FamilyChildFacade {

	Long insertChild(ChildRegistRequest childRegistRequest, Long familyId);

	List<ChildListResponse> readChildList(Long familyId);
}
