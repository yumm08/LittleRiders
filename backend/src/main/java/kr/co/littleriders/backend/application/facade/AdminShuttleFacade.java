package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface AdminShuttleFacade {

	Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Academy academy);
}
