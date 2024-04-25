package kr.co.littleriders.backend.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/teacher")
@RequiredArgsConstructor
public class AdminTeacherController {

	@PostMapping
	public ResponseEntity<Long> addTeacher(@RequestBody @Valid TeacherRegistRequest teacherRegistRequest) {

		// valid 확인
		Academy academy = null;



		return null;
	}
}
