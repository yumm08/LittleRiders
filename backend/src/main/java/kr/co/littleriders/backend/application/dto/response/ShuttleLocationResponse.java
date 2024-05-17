package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleLocationResponse {

    private long shuttleId;
    private double latitude;
    private double longitude;
    private int speed;

    @Setter
    private LocalDateTime time;



    public static ShuttleLocationResponse of(long shuttleId, ShuttleLocationRequest shuttleLocationRequest) {
        return new ShuttleLocationResponse(shuttleId,shuttleLocationRequest.getLatitude(),shuttleLocationRequest.getLongitude(), shuttleLocationRequest.getSpeed(), LocalDateTime.now());
    }

    public static ShuttleLocationResponse of(long shuttleId, double latitude, double longitude, int speed ) {
        return new ShuttleLocationResponse(shuttleId,latitude,longitude,speed, LocalDateTime.now());
    }
}
