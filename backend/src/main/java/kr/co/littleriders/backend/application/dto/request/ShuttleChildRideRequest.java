package kr.co.littleriders.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleChildRideRequest {
    private String beaconNumber;
    private double latitude;
    private double longitude;

    //주석처리 - 김도현

//    public ShuttleChildRide toShuttleChildRide(long shuttleId, long childId) {
//        return ShuttleChildRide.of(shuttleId, childId, this.latitude, this.longitude);
//    }
}
