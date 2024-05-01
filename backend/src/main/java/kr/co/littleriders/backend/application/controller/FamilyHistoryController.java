package kr.co.littleriders.backend.application.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.facade.ChildBoardHistoryFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/family/history")
@RequiredArgsConstructor
public class FamilyHistoryController {

	private final ChildBoardHistoryFacade childBoardHistoryFacade;

	@GetMapping("/child/{childId}")
	public ResponseEntity<ChildBoardHistoryResponse> getChildBoardHistory(@Auth AuthFamily authFamily
																		, @PathVariable(value = "childId") Long childId
																		, @PageableDefault(size = 10, sort = "id") Pageable pageable) {

		Long familyId = authFamily.getId();
		childBoardHistoryFacade.readChildBoardHistory(familyId, childId, pageable);

		return ResponseEntity.ok().body(null);
	}
}
