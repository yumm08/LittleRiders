package kr.co.littleriders.backend.application.facade.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminShuttleFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminShuttleFacadeImpl implements AdminShuttleFacade {

	private final ShuttleService shuttleService;
	private final AcademyService academyService;
	private final String rootPath = "/image/shuttle";


	@Override
	public Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleRegistRequest.toEntity(academy);

		MultipartFile image = shuttleRegistRequest.getImage();
		if(image !=null){
			String imagePath = UUID.randomUUID().toString();
			// 이미지 저장
			shuttle.setImagePath(imagePath);
		}

		return shuttleService.save(shuttle);
	}
}
