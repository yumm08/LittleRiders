package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.station.entity.Station;

public class StationResponse {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    private StationResponse(Station station) {
        this.id = station.getId();
        this.name = station.getName();
        this.latitude = station.getLatitude();
        this.longitude = station.getLongitude();
    }

    public static StationResponse from(Station station) {
        return new StationResponse(station);
    }
}
