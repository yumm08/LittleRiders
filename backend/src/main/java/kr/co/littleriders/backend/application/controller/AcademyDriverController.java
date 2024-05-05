package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyDriverFacade;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

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

	@GetMapping("/{driverId}/image")
	public ResponseEntity<Resource> getDriverImage(@Auth AuthAcademy authAcademy,
												   @PathVariable(value = "driverId") Long driverId) {

		Long academyId = authAcademy.getId();

		Map<String, Object> image = academyDriverFacade.readDriverImage(academyId, driverId);

		Resource imageResource = (Resource) image.get("resource");
		MediaType mediaType = (MediaType) image.get("mediaType");

		HttpHeaders headers = new HttpHeaders();
		if (mediaType != null) {
			headers.setContentType(mediaType);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}

		return ResponseEntity.ok().headers(headers).body(imageResource);
	}
}
