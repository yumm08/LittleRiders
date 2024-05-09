package kr.co.littleriders.backend.domain.beacon;

import java.util.List;

import kr.co.littleriders.backend.application.dto.response.BeaconResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

public interface BeaconServcie {
	List<BeaconResponse> findByAcademy(Academy academy);
}
