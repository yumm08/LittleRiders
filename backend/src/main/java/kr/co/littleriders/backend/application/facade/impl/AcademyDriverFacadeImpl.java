package kr.co.littleriders.backend.application.facade.impl;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyDriverFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AcademyDriverFacadeImpl implements AcademyDriverFacade {

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

	@Override
	public List<AcademyDriverResponse> readDriverList(Long academyId) {

		Academy academy = academyService.findById(academyId);
		List<AcademyDriverResponse> driverList = driverService.findByAcademy(academy)
															  .stream()
															  .sorted(Comparator.comparing(driver -> driver.getStatus() == DriverStatus.WORK ? 0 : 1))
															  .map(AcademyDriverResponse::from)
															  .collect(Collectors.toList());

		return driverList;
	}
}
