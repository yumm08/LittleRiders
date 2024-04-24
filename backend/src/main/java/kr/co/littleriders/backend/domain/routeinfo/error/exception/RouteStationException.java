package kr.co.littleriders.backend.domain.routeinfo.error.exception;

import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class RouteStationException extends LittleRidersException {
    
    RouteStationException(RouteErrorCode errorCode) {
        super(errorCode);

    }

    public static RouteStationException from(RouteErrorCode errorCode) {
        return new RouteStationException(errorCode);
    }

}
