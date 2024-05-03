package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyDriverFacade;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/academy/driver")
@RequiredArgsConstructor
public class AcademyDriverController {

	private final AcademyDriverFacade academyDriverFacade;

	@PostMapping
	public ResponseEntity<Long> addDriver(@Auth AuthAcademy authAcademy,
										  @ModelAttribute @Valid DriverRegistRequest driverRegistRequest) {

		Long academyId = authAcademy.getId();

		Long driverId = academyDriverFacade.insertDriver(driverRegistRequest, academyId);

		return ResponseEntity.ok().body(driverId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyDriverResponse>> getDriverList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyDriverResponse> driverList = academyDriverFacade.readDriverList(academyId);

		return ResponseEntity.ok().body(driverList);
	}
}
