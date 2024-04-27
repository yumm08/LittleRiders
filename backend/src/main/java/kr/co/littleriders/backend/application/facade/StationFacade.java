package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

import java.util.List;

public interface StationFacade {

    void createStation(Academy academy, StationCreateRequest createRequest);

    List<StationResponse> searchByName(String name, Academy academy);

}
