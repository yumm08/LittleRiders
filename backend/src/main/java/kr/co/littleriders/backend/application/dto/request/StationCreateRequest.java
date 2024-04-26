package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StationCreateRequest {
    private String name;
    private double latitude;
    private double longitude;

    public Station toStation(Academy academy) {
        return Station.of(academy, this.name, this.latitude, this.longitude);
    }

}
