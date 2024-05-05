package kr.co.littleriders.backend.application.facade.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.global.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.exception.ChildException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FamilyChildFacadeImpl implements FamilyChildFacade {

	private final ChildService childService;
	private final FamilyService familyService;
	private final AcademyChildService academyChildService;
	private final ImageUtil imageUtil;

	@Override
	public Long insertChild(ChildRegistRequest childRegistRequest, Long familyId) {

		Family family = familyService.findById(familyId);
		Child child = childRegistRequest.toEntity(family);

		MultipartFile image = childRegistRequest.getImage();
		if(image !=null){
			String imagePath = imageUtil.saveImage(image);
			child.setImagePath(imagePath);
		}
		// TODO-이윤지-ChildHistory에도 저장하는 기능 추가

		return childService.save(child);
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

	@Override
	public ChildDetailResponse readChildDetail(Long familyId, Long childId) {

		Family family = familyService.findById(familyId);

		Child child = childService.findById(childId);
		if (!child.equalsFamily(family)) {
			throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
		}

		// TODO-이윤지-자녀 상태 가져오기 (승차중인지 아닌지)
		String status = null;

		List<AcademyList> academyList = academyChildService.findByChild(child)
														   .stream()
														   .map(AcademyChild::getAcademy)
														   .map(AcademyList::from)
														   .collect(Collectors.toList());

		ChildDetailResponse childDetail = ChildDetailResponse.of(child, status, academyList);

		return childDetail;
	}
}
