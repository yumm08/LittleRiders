package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;
import kr.co.littleriders.backend.domain.route.entity.Route;

import java.util.List;

public interface ShuttleFacade {
    List<ShuttleRouteResponse> getRouteList();

    void startDrive(ShuttleStartRequest startRequest);

    void endDrive();

    void recordChildRiding(ShuttleChildRideRequest rideRequest);

    void uploadLocation(ShuttleLocationRequest locationRequest);

}


