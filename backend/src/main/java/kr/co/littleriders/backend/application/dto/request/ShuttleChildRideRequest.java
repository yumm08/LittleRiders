package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRide;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleChildRideRequest {
    private String childCardNumber;
    private double latitude;
    private double longitude;

    public ShuttleChildRide toShuttleChildRide(long shuttleId, long childId) {
        return ShuttleChildRide.of(shuttleId, childId, this.latitude, this.longitude);
    }
}
