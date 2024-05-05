package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyTeacherFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/academy/teacher")
@RequiredArgsConstructor
public class AcademyTeacherController {

	private final AcademyTeacherFacade academyTeacherFacade;

	@PostMapping
//	public ResponseEntity<Long> addTeacher(@Auth AuthAcademy authAcademy,
	public ResponseEntity<Long> addTeacher(
										   @ModelAttribute @Valid TeacherRegistRequest teacherRegistRequest) {

		Long academyId = 1L;

		Long teacherId = academyTeacherFacade.insertTeacher(teacherRegistRequest, academyId);

		return ResponseEntity.ok().body(teacherId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyTeacherResponse>> getTeacherList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyTeacherResponse> teacherList = academyTeacherFacade.readTeacherList(academyId);

		return ResponseEntity.ok().body(teacherList);
	}

	@GetMapping("/{teacherId}/image")
	public ResponseEntity<Resource> getTeacherImage(@Auth AuthAcademy authAcademy,
													@PathVariable(value = "teacherId") Long teacherId) {

		Long academyId = authAcademy.getId();

		Resource resource = academyTeacherFacade.readTeacherImage(academyId, teacherId);

		return ResponseEntity.ok().body(resource);
	}
}
