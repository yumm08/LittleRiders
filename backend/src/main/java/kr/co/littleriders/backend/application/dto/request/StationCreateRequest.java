package kr.co.littleriders.backend.application.dto.request;

import lombok.Getter;

@Getter
public class StationCreateRequest {
    private String name;
    private Double latitude;
    private Double longitude;

}
