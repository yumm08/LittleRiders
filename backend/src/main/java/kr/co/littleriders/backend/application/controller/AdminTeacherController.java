package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminTeacherFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/admin/teacher")
@RequiredArgsConstructor
public class AdminTeacherController {

	private final AdminTeacherFacade adminTeacherFacade;

	@PostMapping
	public ResponseEntity<Long> addTeacher(@Auth AuthAcademy authAcademy,
										   @RequestBody @Valid TeacherRegistRequest teacherRegistRequest) {

		Long academyId = authAcademy.getId();

		Long teacherId = adminTeacherFacade.insertTeacher(teacherRegistRequest, academyId);

		return ResponseEntity.ok().body(teacherId);
	}

	@GetMapping
//	public ResponseEntity<List<AcademyTeacherResponse>> getTeacherList(@Auth AuthAcademy authAcademy) {
	public ResponseEntity<List<AcademyTeacherResponse>> getTeacherList() {

		Long academyId = 1L;

		List<AcademyTeacherResponse> teacherList = adminTeacherFacade.readTeacherList(academyId);

		return ResponseEntity.ok().body(teacherList);
	}
}
