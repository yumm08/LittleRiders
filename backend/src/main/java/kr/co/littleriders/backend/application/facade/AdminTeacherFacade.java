package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface AdminTeacherFacade {
	Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Academy academy);
}
