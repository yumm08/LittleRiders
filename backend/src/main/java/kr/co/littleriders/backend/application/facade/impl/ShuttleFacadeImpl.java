package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.domain.driver.error.exception.DriverException;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.service.ShuttleLocationHistoryService;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShuttleFacadeImpl implements ShuttleFacade {

    private final ShuttleService shuttleService;
    private final RouteService routeService;
    private final DriverService driverService;
    private final TeacherService teacherService;

    private final ShuttleLocationService shuttleLocationService;
    private final ShuttleLocationHistoryService shuttleLocationHistoryService;
    private final ShuttleDriveService shuttleDriveService;
    private final ShuttleChildRideService shuttleChildRideService;

    @Override
    public void startDrive(ShuttleStartRequest startRequest) {

        Long shuttleId = 1L;

        Shuttle shuttle = shuttleService.findById(shuttleId);
        // TODO: shuttleId 퍼미션 체크

        if (routeService.notExistsById(startRequest.getRouteId())) {
            throw RouteException.from(RouteErrorCode.NOT_FOUND);
        }

        if (driverService.notExistsById(startRequest.getDriverId())) {
            throw DriverException.from(DriverErrorCode.NOT_FOUND);
        }

        if (teacherService.notExistsById(startRequest.getTeacherId())) {
            throw TeacherException.from(TeacherErrorCode.NOT_FOUND);
        }

        ShuttleDrive shuttleDrive = startRequest.toShuttleDrive(shuttleId);
        shuttleDriveService.save(shuttleDrive);
    }

    @Override
    public void endDrive() {

        Long shuttleId = 1L;

        // TODO: mongoDB에 저장
        // TODO: redis에서 shuttleId 에 해당하는 데이터 삭제
    }

    @Override
    public void recordChildRiding(ShuttleChildRideRequest rideRequest) {

        Long shuttleId = 1L;

        // TODO: childCardNumber를 childId로 변환
        Long childId = 10L;

        ShuttleChildRide shuttleChildRide = rideRequest.toShuttleChildRide(shuttleId, childId);
        shuttleChildRideService.save(shuttleChildRide);

        // TODO: Response - 아이 이름, 상태(탑승/하차), academyChildId, time,
    }

    @Override
    public void uploadLocation(ShuttleLocationRequest locationRequest) {

        Long shuttleId = 1L;

        ShuttleLocation location = locationRequest.toShuttleLocation(shuttleId);
        shuttleLocationService.save(location);

        ShuttleLocationHistory locationHistory = locationRequest.toShuttleLocationHistory(shuttleId);
        shuttleLocationHistoryService.save(locationHistory);
    }
}
