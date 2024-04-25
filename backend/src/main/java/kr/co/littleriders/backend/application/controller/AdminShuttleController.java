package kr.co.littleriders.backend.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminShuttleFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/shuttle")
@RequiredArgsConstructor
public class AdminShuttleController {

	private final AdminShuttleFacade adminShuttleFacade;

	@PostMapping
	public ResponseEntity<Long> addShuttle(@RequestBody @Validated ShuttleRegistRequest shuttleRegistRequest) {

		// Academy 회원 vaild 확인
		Academy academy = null;

		Long shuttleId = adminShuttleFacade.insertShuttle(shuttleRegistRequest, academy);

		return ResponseEntity.ok().body(shuttleId);
	}


}
