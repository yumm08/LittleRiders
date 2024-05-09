package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.client.SmsFetchAPI;
import kr.co.littleriders.backend.application.client.SmsSendClientRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.*;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.domain.driver.error.exception.DriverException;
import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.domain.shuttle.*;
import kr.co.littleriders.backend.domain.shuttle.dto.ShuttleLocationDTO;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import kr.co.littleriders.backend.domain.shuttle.service.ShuttleLocationHistoryService;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
    private final DriveUniqueKeyService driveUniqueKeyService;
    private final ShuttleChildRideService shuttleChildRideService;
    private final SseFacade sseFacade;
    private final SmsFetchAPI smsFetchAPI;


    private final ShuttleDriveHistoryService shuttleDriveHistoryService;

    @Override
    public List<RouteResponse> getRouteList(AuthTerminal authTerminal) {
        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        List<Route> routeList = routeService.findAllByAcademy(academy);

        return routeList.stream()
                .map(RouteResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteDetailResponse> getRouteListWithStation(AuthTerminal authTerminal) {
        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        List<Route> routeList = routeService.findAllByAcademy(academy);

        return routeList.stream()
                .map(RouteDetailResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailResponse getRoute(AuthTerminal authTerminal, long routeId) {
        Route route = routeService.findById(routeId);

        Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        long academyId = shuttle.getAcademy().getId();

        if(!Objects.equals(academyId, route.getAcademy().getId())) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }

        return RouteDetailResponse.from(route);
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
        Teacher teacher = teacherService.findById(startRequest.getTeacherId());
        Driver driver = driverService.findById(startRequest.getDriverId());
        Route route = routeService.findById(startRequest.getRouteId());

        if (!Objects.equals(shuttle.getAcademy().getId(), route.getAcademy().getId())) {
            throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
        }

        // 셔틀 정보 저장
        ShuttleDrive shuttleDrive = startRequest.toShuttleDrive(shuttleId);
        shuttleDriveService.save(shuttleDrive);

        // 탑승 원생 리스트
        List<AcademyChild> academyChildList = route.getRouteStationList().stream()
                .flatMap(station -> station.getChildBoardInfoList().stream())
                .map(ChildBoardDropInfo::getAcademyChild)
                .toList();

        List<SmsSendClientRequest> smsSendClientRequestList = new ArrayList<>();

        academyChildList.forEach(academyChild -> {
            String uuid = UUID.randomUUID().toString();
            long academyChildId = academyChild.getAcademy().getId();
            DriveUniqueKey driveUniqueKey = DriveUniqueKey.of(uuid, shuttleId, academyChildId);
            driveUniqueKeyService.save(driveUniqueKey);

            smsSendClientRequestList.add(SmsSendClientRequest.toStartDriveMessage(
                    uuid, academyChild, teacher, driver, shuttle
            ));
        });

        // 운행 시작 sms 발송
        smsFetchAPI.sendLMS(smsSendClientRequestList);
    }

    @Override
    public void endDrive(long shuttleId) {


        //TODO - 김도현 - ifnotexists then throw error

        //실시간 위도 경도 정보
        ShuttleLocationHistory shuttleLocationHistory = shuttleLocationHistoryService.findByShuttleId(shuttleId);
        //실시간 운행자 정보
        ShuttleDrive shuttleDrive = shuttleDriveService.findByShuttleId(shuttleId);

        //실시간 어린이 승하차 내역
        ShuttleChildRide shuttleChildRide = shuttleChildRideService.findByShuttleId(shuttleId);
        //dto 변환
        List<ShuttleLocationDTO> shuttleLocationDTOList = shuttleLocationHistory.getLocationInfoList()
                .stream().map(ShuttleLocationDTO::from)
                .toList();
        long driverId = shuttleDrive.getDriverId();
        long teacherId = shuttleDrive.getTeacherId();

        Driver driver = driverService.findById(driverId);
        Teacher teacher = teacherService.findById(teacherId);
        Shuttle shuttle  = shuttleService.findById(shuttleId);
        LocalDateTime start = shuttleDrive.getTime();
        LocalDateTime end = LocalDateTime.now();
        ShuttleDriveHistory shuttleDriveHistory = ShuttleDriveHistory.of(
                start,
                end,
                shuttle,
                driver,
                teacher,
                shuttleLocationDTOList);

        //TODO - 김도현 - 어린이 저장 및 정상 종료 여부 확인하기
        //mongoDB 에 저장
        shuttleDriveHistoryService.save(shuttleDriveHistory);

        //현재 위치도 지워버려야함
        shuttleLocationHistoryService.delete(shuttleLocationHistory);
        shuttleDriveService.delete(shuttleDrive);
        shuttleChildRideService.delete(shuttleChildRide);

        ShuttleLocation shuttleLocation = shuttleLocationService.findByShuttleId(shuttleId);
        shuttleLocationService.delete(shuttleLocation);

        //TODO - 김도현: 어린이 삭제 필요

        //TODO - 김도현 : 운행종료 노티 모두 던지기

    }


    @Override
    public ShuttleChildRideResponse recordChildRiding(AuthTerminal authTerminal, ShuttleChildRideRequest rideRequest) {

        long shuttleId = authTerminal.getShuttleId();

        // TODO: 졸업을 한 아이에 대한 valid check 추가

        // AcademyChild academyChild = academyChildService.findByBeaconNumber(rideRequest.getBeaconNumber());
        //TODO - HOTFIX - 이수현 - 수정 필요. ShuttleChildRide 가 필요없어짐.
        ShuttleChildRide shuttleChildRide = rideRequest.toShuttleChildRide(shuttleId, 0);
        shuttleChildRideService.save(shuttleChildRide);

        // return ShuttleChildRideResponse.of(academyChild,shuttleChildRide);
        return null;
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
        sseFacade.broadcastShuttleLocation(shuttleId,locationRequest);

    }

    @Override
    public DriverInfoResponse getDriverInfoByCardNumber(AuthTerminal authTerminal, String cardNumber) {

        //TODO - 김도현 - Permission check

        Driver driver = driverService.findByCardNumber(cardNumber);
//        Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        //permissionHelper.check(shuttle,driver);

        //TODO - 김도현 - if not Work then throw exception

        return DriverInfoResponse.from(driver);


    }

    @Override
    public TeacherInfoResponse getTeacherInfoByCardNumber(AuthTerminal authTerminal, String cardNumber) {

        //TODO - 김도현 - Permission check
        Teacher teacher = teacherService.findByCardNumber(cardNumber);
        //Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        //permissionHelper.check(shuttle,teacher);


        return TeacherInfoResponse.from(teacher);

        //TODO - 김도현 - if not Work then throw exception

    }


}
