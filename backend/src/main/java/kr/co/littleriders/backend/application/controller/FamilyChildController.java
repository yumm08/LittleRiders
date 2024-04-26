package kr.co.littleriders.backend.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/family/child")
@RequiredArgsConstructor
public class FamilyChildController {

	private final FamilyChildFacade familyChildFacade;

	@PostMapping
	public ResponseEntity<Long> addChild(@RequestBody @Valid ChildRegistRequest childRegistRequest) {

		Long familyId = 1L; // 검증은 service에서 해야 함
		Long childId = familyChildFacade.insertChild(childRegistRequest, familyId);

		return ResponseEntity.ok().body(childId);
	}

	@GetMapping
	public ResponseEntity<List<ChildListResponse>> getChildList() {

		Long familyId = 1L; // 검증 자체는 service에서 해야 함
		List<ChildListResponse> childList = familyChildFacade.readChildList(familyId);

		return ResponseEntity.ok().body(childList);
	}
}
