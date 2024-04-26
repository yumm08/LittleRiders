package kr.co.littleriders.backend.application.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FamilyChildFacadeImpl implements FamilyChildFacade {

	private final ChildService childService;
	private final String rootPath = "/image/child";

	@Override
	public Long insertChild(ChildRegistRequest childRegistRequest, Family family) {

		Child child;

		// 성별
		Gender gender = Enum.valueOf(Gender.class, childRegistRequest.getGender());

		// 이미지 파일이 있는 경우 이미지 경로 설정
		if (childRegistRequest.getImage() != null) {
			String imagePath = rootPath;
			child = Child.of(childRegistRequest, gender, family, imagePath);
		} else {
			child = Child.of(childRegistRequest, gender, family);
		}

		return childService.save(child);
	}

	@Override
	public List<ChildListResponse> readChildList(Long familyId) {

		// family 검증 코드 필수

		List<ChildListResponse> childList = childService.findByFamilyId(familyId)
														.stream()
														.map(ChildListResponse::from)
														.collect(Collectors.toList());

		return childList;
	}
}
