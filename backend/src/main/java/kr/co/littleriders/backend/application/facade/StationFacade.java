package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;

import java.util.List;

public interface StationFacade {

    void createStation(AuthAcademy authAcademy, StationRequest createRequest);

    List<StationResponse> searchByName(String name, AuthAcademy authAcademy);

}
