package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import lombok.Getter;

@Getter
public class ShuttleStartRequest {
    private long routeId;
    private long driverId;
    private long teacherId;

    public ShuttleDrive toShuttleDrive(long shuttleId) {
        return ShuttleDrive.of(shuttleId, this.routeId, this.driverId, this.teacherId);
    }

}
