package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyShuttleFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academy/shuttle")
@RequiredArgsConstructor
public class AcademyShuttleController {

	private final AcademyShuttleFacade academyShuttleFacade;

	@PostMapping
	public ResponseEntity<Long> addShuttle(@Auth AuthAcademy authAcademy,
										   @ModelAttribute @Valid ShuttleRegistRequest shuttleRegistRequest) {

		Long academyId = authAcademy.getId();

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

		Map<String, Object> image = academyShuttleFacade.readShuttleImage(academyId, shuttleId);

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
