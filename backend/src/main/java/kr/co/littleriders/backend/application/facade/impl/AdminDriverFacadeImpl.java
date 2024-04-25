package kr.co.littleriders.backend.application.facade.impl;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminDriverFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminDriverFacadeImpl implements AdminDriverFacade {

	private final DriverService driverService;

	@Override
	public Long insertDriver(DriverRegistRequest driverRegistRequest, Academy academy) {

		Driver driver;
		// 동일한 academy 추가되는 license number unique 해야함

		if (driverRegistRequest.getImage() != null) {
			// 이미지 저장
			String imagePath = null;

			driver = Driver.of(driverRegistRequest, academy, DriverStatus.WORK, imagePath);
		} else {
			driver = Driver.of(driverRegistRequest, academy, DriverStatus.WORK);
		}


		return driverService.save(driver);
	}
}
