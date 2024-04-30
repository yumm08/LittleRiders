package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;

public interface ShuttleFacade {

    void startDrive(ShuttleStartRequest startRequest);

    void endDrive();

    void recordChildRiding(ShuttleChildRideRequest rideRequest);

    void uploadLocation(ShuttleLocationRequest locationRequest);

}


