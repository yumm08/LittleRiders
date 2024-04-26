package kr.co.littleriders.backend.application.facade.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminDriverFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminDriverFacadeImpl implements AdminDriverFacade {

	private final DriverService driverService;
	private final AcademyService academyService;
	private final String rootPath = "/image/driver";

	@Override
	public Long insertDriver(DriverRegistRequest driverRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Driver driver = driverRegistRequest.toEntity(academy);

		MultipartFile image = driverRegistRequest.getImage();
		if(image !=null){
			String imagePath = UUID.randomUUID().toString();
			// 이미지 저장
			driver.setImagePath(imagePath);
		}

		return driverService.save(driver);
	}
}
