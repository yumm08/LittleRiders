package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleChildRideResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleRouteResponse;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.domain.driver.error.exception.DriverException;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import kr.co.littleriders.backend.domain.shuttle.service.ShuttleLocationHistoryService;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShuttleFacadeImpl implements ShuttleFacade {

    private final ShuttleService shuttleService;
    private final RouteService routeService;
    private final DriverService driverService;
    private final TeacherService teacherService;
    private final AcademyChildService academyChildService;

    private final ShuttleLocationService shuttleLocationService;
    private final ShuttleLocationHistoryService shuttleLocationHistoryService;
    private final ShuttleDriveService shuttleDriveService;
    private final ShuttleChildRideService shuttleChildRideService;

    @Override
    public List<ShuttleRouteResponse> getRouteList(AuthTerminal authTerminal) {
        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        List<Route> routeList = routeService.findAllByAcademy(academy);

        return routeList.stream()
                .map(ShuttleRouteResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void startDrive(AuthTerminal authTerminal, ShuttleStartRequest startRequest) {

        long shuttleId = authTerminal.getShuttleId();

        if (routeService.notExistsById(startRequest.getRouteId())) {
            throw RouteException.from(RouteErrorCode.NOT_FOUND);
        }

        if (driverService.notExistsById(startRequest.getDriverId())) {
            throw DriverException.from(DriverErrorCode.NOT_FOUND);
        }

        if (teacherService.notExistsById(startRequest.getTeacherId())) {
            throw TeacherException.from(TeacherErrorCode.NOT_FOUND);
        }

        Shuttle shuttle = shuttleService.findById(shuttleId);
        Route route = routeService.findById(startRequest.getRouteId());

        if (!Objects.equals(shuttle.getAcademy().getId(), route.getAcademy().getId())) {
            throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
        }

        ShuttleDrive shuttleDrive = startRequest.toShuttleDrive(shuttleId);
        shuttleDriveService.save(shuttleDrive);
    }

    @Override
    public void endDrive(AuthTerminal authTerminal) {

        long shuttleId = authTerminal.getShuttleId();

        // TODO: mongoDB에 저장
        // TODO: redis에서 shuttleId 에 해당하는 데이터 삭제
    }

    @Override
    public ShuttleChildRideResponse recordChildRiding(AuthTerminal authTerminal, ShuttleChildRideRequest rideRequest) {

        long shuttleId = authTerminal.getShuttleId();

        // TODO: 탈퇴한 회원에 대한 valid check 추가

        AcademyChild academyChild = academyChildService.findByCardNumber(rideRequest.getChildCardNumber());
        Long childId = academyChild.getChild().getId();

        ShuttleChildRide shuttleChildRide = rideRequest.toShuttleChildRide(shuttleId, childId);
        shuttleChildRideService.save(shuttleChildRide);

        return ShuttleChildRideResponse.of(academyChild,shuttleChildRide);
    }

    @Override
    public void uploadLocation(AuthTerminal authTerminal, ShuttleLocationRequest locationRequest) {

        long shuttleId = authTerminal.getShuttleId();

        ShuttleLocation location = locationRequest.toShuttleLocation(shuttleId);
        shuttleLocationService.save(location);

        ShuttleLocationHistory locationHistory;

        if (shuttleLocationHistoryService.existsByShuttleId(shuttleId)) {
            locationHistory = shuttleLocationHistoryService.findByShuttleId(shuttleId);
            double latitude = locationRequest.getLatitude();
            double longitude = locationRequest.getLongitude();
            int speed = locationRequest.getSpeed();
            locationHistory.addLocation(latitude, longitude, speed);
        } else {
            locationHistory = locationRequest.toShuttleLocationHistory(shuttleId);
        }

        shuttleLocationHistoryService.save(locationHistory);
    }

}
