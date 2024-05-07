package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleLocationResponse {
    private double latitude;
    private double longitude;
    private int speed;

    public static ShuttleLocationResponse of(double latitude, double longitude, int speed) {
        return new ShuttleLocationResponse(latitude,longitude,speed);
    }

    public static ShuttleLocationResponse from(ShuttleLocationRequest shuttleLocationRequest) {
        return ShuttleLocationResponse.of(shuttleLocationRequest.getLatitude() ,shuttleLocationRequest.getLongitude(),shuttleLocationRequest.getSpeed());
    }
}
