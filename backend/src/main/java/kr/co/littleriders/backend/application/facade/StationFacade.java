package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationFacade {

    void createStation(Academy academy, StationCreateRequest createRequest);

    Page<StationResponse> searchByName(String name, Academy academy, Pageable pageable);

}
