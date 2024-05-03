package kr.co.littleriders.backend.application.facade.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FamilyChildFacadeImpl implements FamilyChildFacade {

	private final ChildService childService;
	private final FamilyService familyService;
	private final String rootPath = "/image/child";

	@Override
	public Long insertChild(ChildRegistRequest childRegistRequest, Long familyId) {

		Family family = familyService.findById(familyId);
		Child child = childRegistRequest.toEntity(family);

		MultipartFile image = childRegistRequest.getImage();
		if(image !=null){
			String imagePath = UUID.randomUUID().toString();
			// 이미지 저장
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
}
