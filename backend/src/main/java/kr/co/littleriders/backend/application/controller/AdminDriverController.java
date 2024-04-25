package kr.co.littleriders.backend.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminDriverFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/driver")
@RequiredArgsConstructor
public class AdminDriverController {

	private final AdminDriverFacade adminDriverFacade;

	@PostMapping
	public ResponseEntity<Long> addDriver(@RequestBody @Valid DriverRegistRequest driverRegistRequest) {

		// Academy 회원 vaild 확인
		Academy academy = null;

		Long driverId = adminDriverFacade.insertDriver(driverRegistRequest, academy);

		return ResponseEntity.ok().body(driverId);
	}
}
