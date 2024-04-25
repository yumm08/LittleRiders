package kr.co.littleriders.backend.application.facade.impl;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminTeacherFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminTeacherFacadeImpl implements AdminTeacherFacade {

	private final TeacherService teacherService;

	@Override
	public Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Academy academy) {

		Teacher teacher;

		if (teacherRegistRequest.getImage() != null) {
			String imagePath = null;

			teacher = Teacher.of(teacherRegistRequest, academy, TeacherStatus.WORK, imagePath);
		} else {
			teacher = Teacher.of(teacherRegistRequest, academy, TeacherStatus.WORK);
		}

		return teacherService.save(teacher);
	}
}
