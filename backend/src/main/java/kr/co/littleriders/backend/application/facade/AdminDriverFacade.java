package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface AdminDriverFacade {
	Long insertDriver(DriverRegistRequest driverRegistRequest, Long academyId);
}
