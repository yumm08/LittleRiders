package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {
    private long id;
    private String name;

    public static RouteResponse from(Route route) {
        long id = route.getId();
        String name = route.getName();

        return new RouteResponse(id, name);
    }
}