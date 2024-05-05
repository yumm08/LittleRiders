package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleChildRideResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;

import java.util.List;

public interface ShuttleFacade {
    List<ShuttleRouteResponse> getRouteList(AuthTerminal authTerminal);

    void startDrive(AuthTerminal authTerminal, ShuttleStartRequest startRequest);

    void endDrive(long shuttleId);

    ShuttleChildRideResponse recordChildRiding(AuthTerminal authTerminal, ShuttleChildRideRequest rideRequest);

    void uploadLocation(AuthTerminal authTerminal, ShuttleLocationRequest locationRequest);

}


