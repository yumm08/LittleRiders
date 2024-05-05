package kr.co.littleriders.backend.domain.shuttle.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "shuttle_child_ride")
@Getter
public class ShuttleChildRide {
    @Id
    private long shuttleId;

    private List<RideInfo> rideInfoList;

    private ShuttleChildRide(final long shuttleId, List<RideInfo> rideInfoList) {
        this.shuttleId = shuttleId;
        this.rideInfoList = rideInfoList;
    }

    public static ShuttleChildRide of(final long shuttleId, final long childId, final double latitude, final double longitude) {
        List<RideInfo> list = new ArrayList<>();
        list.add(new RideInfo(childId, latitude, longitude));
        return new ShuttleChildRide(shuttleId, list);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RideInfo {
        private long academyChildId;

        private double latitude;

        private double longitude;

        private LocalDateTime time;

        private RideInfo(final long academyChildId, final double latitude, final double longitude) {
            this.academyChildId = academyChildId;
            this.latitude = latitude;
            this.longitude = longitude;
            this.time = LocalDateTime.now();
        }
    }

}
