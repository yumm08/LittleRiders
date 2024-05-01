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
@RedisHash(value = "shuttle_location_history")
@Getter
public class ShuttleLocationHistory {

    @Id
    private long shuttleId;

    private List<LocationInfo> locationInfoList;

    private ShuttleLocationHistory(final long shuttleId, List<LocationInfo> locationInfoList) {
        this.shuttleId = shuttleId;
        this.locationInfoList = locationInfoList;
    }

    public static ShuttleLocationHistory of(final long shuttleId, double latitude, double longitude, int speed) {
        List<LocationInfo> list = new ArrayList<>();
        list.add(new LocationInfo(latitude, longitude, speed));
        return new ShuttleLocationHistory(shuttleId, list);
    }

    public void addLocation(double latitude, double longitude, int speed) {
        LocationInfo newLocationInfo = new LocationInfo(latitude, longitude, speed);
        this.locationInfoList.add(newLocationInfo);
    }

    @Getter
    public static class LocationInfo {
        private double latitude;

        private double longitude;

        private int speed;

        private LocalDateTime time;

        private LocationInfo(double latitude, double longitude, int speed) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.speed = speed;
            this.time = LocalDateTime.now();
        }
    }
}
