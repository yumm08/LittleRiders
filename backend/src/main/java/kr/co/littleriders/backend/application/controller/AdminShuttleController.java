package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminShuttleFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/academy/shuttle")
@RequiredArgsConstructor
public class AdminShuttleController {

	private final AdminShuttleFacade adminShuttleFacade;

	@PostMapping
	public ResponseEntity<Long> addShuttle(@Auth AuthAcademy authAcademy,
										   @RequestBody @Valid ShuttleRegistRequest shuttleRegistRequest) {

		Long academyId = authAcademy.getId();

		Long shuttleId = adminShuttleFacade.insertShuttle(shuttleRegistRequest, academyId);

		return ResponseEntity.ok().body(shuttleId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyShuttleResponse>> getShuttleList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyShuttleResponse> shuttleList = adminShuttleFacade.readShuttleList(academyId);

		return ResponseEntity.ok().body(shuttleList);
	}


}
