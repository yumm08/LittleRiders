package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShuttleRouteResponse {
    private long id;
    private String name;

    public static ShuttleRouteResponse from(Route route) {
        long id = route.getId();
        String name = route.getName();

        return new ShuttleRouteResponse(id, name);
    }

}
