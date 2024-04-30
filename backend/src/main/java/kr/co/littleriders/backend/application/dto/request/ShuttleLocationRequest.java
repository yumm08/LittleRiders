package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;
import lombok.Getter;

@Getter
public class ShuttleLocationRequest {
    private double latitude;
    private double longitude;
    private int speed;

    public ShuttleLocation toShuttleLocation(long shuttleId) {
        return ShuttleLocation.of(shuttleId, this.latitude, this.longitude, this.speed);
    }

    public ShuttleLocationHistory toShuttleLocationHistory(long shuttleId) {
        return ShuttleLocationHistory.of(shuttleId, this.latitude, this.longitude, this.speed);
    }

}
