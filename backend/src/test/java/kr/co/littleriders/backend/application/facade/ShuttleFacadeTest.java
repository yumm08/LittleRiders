//package kr.co.littleriders.backend.application.facade;
//
//import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
//import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
//import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
//import kr.co.littleriders.backend.common.fixture.AcademyFixture;
//import kr.co.littleriders.backend.common.fixture.DriverFixture;
//import kr.co.littleriders.backend.common.fixture.ShuttleFixture;
//import kr.co.littleriders.backend.common.fixture.TeacherFixture;
//import kr.co.littleriders.backend.domain.academy.AcademyChildService;
//import kr.co.littleriders.backend.domain.academy.AcademyService;
//import kr.co.littleriders.backend.domain.academy.entity.*;
//import kr.co.littleriders.backend.domain.driver.DriverService;
//import kr.co.littleriders.backend.domain.driver.entity.Driver;
//import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
//import kr.co.littleriders.backend.domain.route.RouteService;
//import kr.co.littleriders.backend.domain.route.entity.Route;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
//import kr.co.littleriders.backend.domain.shuttle.entity.*;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleChildRideException;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleDriveException;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationException;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationHistoryException;
//import kr.co.littleriders.backend.domain.shuttle.service.ShuttleLocationHistoryService;
//import kr.co.littleriders.backend.domain.teacher.TeacherService;
//import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
//import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
//import kr.co.littleriders.backend.domain.terminal.TerminalService;
//import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
//import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
//import kr.co.littleriders.backend.global.entity.Gender;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@Transactional
//@SpringBootTest
//public class ShuttleFacadeTest {
//
//    @Autowired
//    private ShuttleFacade shuttleFacade;
//
//    @Autowired
//    private AcademyService academyService;
//
//    @Autowired
//    private TerminalService terminalService;
//
//    @Autowired
//    private ShuttleService shuttleService;
//
//    @Autowired
//    private RouteService routeService;
//
//    @Autowired
//    private DriverService driverService;
//
//    @Autowired
//    private TeacherService teacherService;
//
//
//    @Autowired
//    private AcademyChildService academyChildService;
//
//    private Academy academy;
//
//    private AuthTerminal authTerminal;
//
//    @Autowired
//    ShuttleLocationHistoryService shuttleLocationHistoryService;
//
//    @Autowired
//    ShuttleDriveService shuttleDriveService;
//
//    @Autowired
//    ShuttleChildRideService shuttleChildRideService;
//
//    @Autowired
//    ShuttleLocationService shuttleLocationService;
//
//
//    @BeforeEach
//    void setUP() {
//        academy = Academy.of("a123@gmail.com", "1234", "어린이집B", "서울시 강남구", "010-1111-1111", 35.6, 23.8);
//        academyService.save(academy);
//        Terminal terminal = Terminal.of(academy, "bbbb");
//        terminalService.save(terminal);
//        Shuttle shuttle = Shuttle.of("license1234", "2호차", "a", academy, ShuttleStatus.USE);
//        shuttleService.save(shuttle);
//        authTerminal = AuthTerminal.of(terminal, shuttle);
//    }
//
//    @Nested
//    @DisplayName("운행 가능 노선 목록 조회")
//    class getRouteList {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            shuttleFacade.getRouteList(authTerminal);
//        }
//    }
//
//    @Nested
//    @DisplayName("운행 가능 노선 상세 조회")
//    class getRoute {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            Route route = Route.of(academy, "등원A", "board");
//            long routeId = routeService.save(route);
//
//            shuttleFacade.getRoute(authTerminal, routeId);
//        }
//    }
//
//    @Nested
//    @DisplayName("운행 시작")
//    class startDrive {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            Route route = Route.of(academy, "등원A", "board");
//            routeService.save(route);
//            Driver driver = Driver.of("이름", "010-1111-1111", academy, DriverStatus.WORK);
//            driverService.save(driver);
//            Teacher teacher = Teacher.of("이름", "010-2222-2222", academy, TeacherStatus.WORK);
//            teacherService.save(teacher);
//            ShuttleStartRequest shuttleStartRequest = new ShuttleStartRequest(route.getId(), driver.getId(), teacher.getId());
//            shuttleFacade.startDrive(authTerminal, shuttleStartRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("원생 승하차")
//    class recordChildRiding {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            Family family = Family.of("a123@gmail.com", "1234", "이름", "주소", "010-2222-2222");
//            familyService.save(family);
//            Child child = Child.of("아이", LocalDate.parse("2000-01-01"), Gender.MALE, family);
//            childService.save(child);
//            AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
//            academyFamilyService.save(academyFamily);
//            AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BARCODE);
//            academyChildService.save(academyChild);
//
//            ShuttleChildRideRequest shuttleChildRideRequest = new ShuttleChildRideRequest(academyChild.getCardNumber(), 23.4, 22.2);
//            shuttleFacade.recordChildRiding(authTerminal, shuttleChildRideRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("위도 경도 업로드")
//    class uploadLocation {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            ShuttleLocationRequest shuttleLocationRequest = new ShuttleLocationRequest(33.3, 45.2, 75);
//            shuttleFacade.uploadLocation(authTerminal, shuttleLocationRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("endDrive 테스트")
//    class endDrive{
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess(){
//            //given
//            Academy academy = AcademyFixture.BOXING.toAcademy();
//            academyService.save(academy);
//
//            Shuttle shuttle = ShuttleFixture.HO_11.toShuttle(academy,ShuttleStatus.USE);
//            shuttleService.save(shuttle);
//
//            Driver driver = DriverFixture.YOON.toDriver(academy,DriverStatus.WORK);
//            driverService.save(driver);
//
//            Teacher teacher = TeacherFixture.NAM.toTeacher(academy,TeacherStatus.WORK);
//
//            teacherService.save(teacher);
//
//
//            long shuttleId = shuttle.getId();
//            long teacherId = teacher.getId();
//            long driverId = driver.getId();
//
//            double latitude = 34.123;
//            double longitude = 126.123;
//            int speed = 14;
//            ShuttleLocationHistory shuttleLocationHistory = ShuttleLocationHistory.of(shuttleId,latitude,longitude,speed);
//            ShuttleDrive shuttleDrive = ShuttleDrive.of(shuttleId,1,driverId,teacherId);
//            ShuttleChildRide shuttleChildRide = ShuttleChildRide.of(shuttleId,1,latitude,longitude);
//            ShuttleLocation shuttleLocation = ShuttleLocation.of(shuttleId,latitude,longitude,speed);
//
//            shuttleLocationService.save(shuttleLocation);
//            shuttleLocationHistoryService.save(shuttleLocationHistory);
//            shuttleDriveService.save(shuttleDrive);
//            shuttleChildRideService.save(shuttleChildRide);
//
//            //when
//            shuttleFacade.endDrive(shuttleId);
//            //then
//
//            assertThrows(ShuttleLocationException.class,
//                    () -> shuttleLocationService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleLocationHistoryException.class,
//                    () -> shuttleLocationHistoryService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleDriveException.class,
//                    () -> shuttleDriveService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleChildRideException.class,
//                    () -> shuttleChildRideService.findByShuttleId(shuttleId));
//
//
//
//        }
//
//    }
//}
