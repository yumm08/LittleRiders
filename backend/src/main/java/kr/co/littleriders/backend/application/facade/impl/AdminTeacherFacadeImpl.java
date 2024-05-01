package kr.co.littleriders.backend.application.facade.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminTeacherFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminTeacherFacadeImpl implements AdminTeacherFacade {

	private final TeacherService teacherService;
	private final AcademyService academyService;
	private final String rootPath = "/image/teacher";

	@Override
	public Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Teacher teacher = teacherRegistRequest.toEntity(academy);

		MultipartFile image = teacherRegistRequest.getImage();
		if(image !=null){
			String imagePath = UUID.randomUUID().toString();
			// 이미지 저장
			teacher.setImagePath(imagePath);
		}

		return teacherService.save(teacher);
	}

	@Override
	public List<AcademyTeacherResponse> readTeacherList(Long academyId) {

		Academy academy = academyService.findById(academyId);
		List<AcademyTeacherResponse> teacherList = teacherService.findByAcademy(academy)
													.stream()
													.map(AcademyTeacherResponse::from)
													.collect(Collectors.toList());

		return teacherList;
	}
}
