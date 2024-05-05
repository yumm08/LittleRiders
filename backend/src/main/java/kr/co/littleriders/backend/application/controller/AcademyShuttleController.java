package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyShuttleFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/academy/shuttle")
@RequiredArgsConstructor
public class AcademyShuttleController {

	private final AcademyShuttleFacade academyShuttleFacade;

	@PostMapping
//	public ResponseEntity<Long> addShuttle(@Auth AuthAcademy authAcademy,

	public ResponseEntity<Long> addShuttle(
										   @ModelAttribute @Valid ShuttleRegistRequest shuttleRegistRequest) {

		Long academyId = 1L;

		Long shuttleId = academyShuttleFacade.insertShuttle(shuttleRegistRequest, academyId);

		return ResponseEntity.ok().body(shuttleId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyShuttleResponse>> getShuttleList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyShuttleResponse> shuttleList = academyShuttleFacade.readShuttleList(academyId);

		return ResponseEntity.ok().body(shuttleList);
	}

	@GetMapping("/{shuttleId}/image")
	public ResponseEntity<Resource> getShuttleImage(@Auth AuthAcademy authAcademy,
												   @PathVariable(value = "shuttleId") Long shuttleId) {

		Long academyId = authAcademy.getId();

		Resource resource = academyShuttleFacade.readShuttleImage(academyId, shuttleId);

		return ResponseEntity.ok().body(resource);
	}

}
