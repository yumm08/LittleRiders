package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleChildRideResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;

import java.util.List;

public interface ShuttleFacade {
    List<ShuttleRouteResponse> getRouteList();

    void startDrive(ShuttleStartRequest startRequest);

    void endDrive(long shuttleId);

    ShuttleChildRideResponse recordChildRiding(ShuttleChildRideRequest rideRequest);

    void uploadLocation(ShuttleLocationRequest locationRequest);

}


