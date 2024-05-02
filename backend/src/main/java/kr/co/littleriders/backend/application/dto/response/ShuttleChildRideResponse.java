package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRide;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRideStatus;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleChildRideErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleChildRideException;

import java.time.LocalDateTime;
import java.util.List;

public class ShuttleChildRideResponse {
    private String name;
    private String status;
    private long academyChildId;
    private LocalDateTime time;

    public static ShuttleChildRideResponse of(AcademyChild academyChild, ShuttleChildRide shuttleChildRide) {
        ShuttleChildRideResponse response = new ShuttleChildRideResponse();

        response.name = academyChild.getChild().getName();
        response.academyChildId = academyChild.getId();

        List<ShuttleChildRide.RideInfo> ChildRideInfoList = shuttleChildRide.getRideInfoList().stream()
                .filter(rideInfo -> rideInfo.getAcademyChildId() == academyChild.getId())
                .toList();

        // 태깅 횟수에 따라 status 설정 및 시간 설정
        if (ChildRideInfoList.size() == 1) {
            response.status = String.valueOf(ShuttleChildRideStatus.BOARD);
            response.time = ChildRideInfoList.get(0).getTime();
        } else if (ChildRideInfoList.size() == 2) {
            response.status = String.valueOf(ShuttleChildRideStatus.DROP);
            response.time = ChildRideInfoList.get(1).getTime();
        } else {
            throw ShuttleChildRideException.from(ShuttleChildRideErrorCode.INVALID_RIDE_STATUS);
        }

        return response;
    }
}
