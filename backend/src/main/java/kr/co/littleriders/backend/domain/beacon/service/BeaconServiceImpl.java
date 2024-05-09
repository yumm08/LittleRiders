package kr.co.littleriders.backend.domain.beacon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.response.BeaconResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class BeaconServiceImpl implements BeaconServcie {

	private final BeaconRepository beaconRepository;

	@Override
	public List<BeaconResponse> findByAcademy(Academy academy) {
		return beaconRepository.findByAcademy(academy);
	}
}
