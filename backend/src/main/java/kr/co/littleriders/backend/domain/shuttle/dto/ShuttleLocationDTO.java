package kr.co.littleriders.backend.domain.shuttle.dto;


import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShuttleLocationDTO {


    private final double latitude;
    private final double longitude;
    private final LocalDateTime time;

    private ShuttleLocationDTO(double latitude, double longitude, LocalDateTime time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public static ShuttleLocationDTO of (double latitude, double longitude, LocalDateTime time){
        return new ShuttleLocationDTO(latitude,longitude,time);
    }

    public static ShuttleLocationDTO from(ShuttleLocationHistory.LocationInfo locationInfo){
        double latitude = locationInfo.getLatitude();
        double longitude = locationInfo.getLongitude();
        LocalDateTime time = locationInfo.getTime();
        return ShuttleLocationDTO.of(latitude,longitude,time);
    }

}
