package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.shuttle.ShuttleChildRideService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleChildRideException;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleDriveException;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationException;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationHistoryException;
import kr.co.littleriders.backend.domain.shuttle.service.ShuttleLocationHistoryService;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
class ShuttleFacadeTest {

    @Autowired
    ShuttleLocationHistoryService shuttleLocationHistoryService;

    @Autowired
    ShuttleDriveService shuttleDriveService;

    @Autowired
    ShuttleChildRideService shuttleChildRideService;

    @Autowired
    AcademyService academyService;

    @Autowired
    ShuttleService shuttleService;

    @Autowired
    DriverService driverService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ShuttleLocationService shuttleLocationService;

    @Autowired
    ShuttleFacade shuttleFacade;


    @Nested
    @DisplayName("endDrive 테스트")
    class endDrive{

        @Test
        @DisplayName("성공")
        void whenSuccess(){
            //given
            Academy academy = Academy.of(
                    "a",
                    "b",
                    "c",
                    "d",
                    "e",
                    3,
                    4
            );
            academyService.save(academy);
            Shuttle shuttle = Shuttle.of(
                    "가1234",
                    "이름",
                    "타입",
                    academy,
                    ShuttleStatus.USE
            );
            shuttleService.save(shuttle);

            Driver driver = Driver.of(
                    "기사",
                    "전화번호",
                    academy,
                    DriverStatus.WORK
            );
            driverService.save(driver);
            Teacher teacher = Teacher.of(
                    "선생",
                    "전화번호",
                    academy,
                    TeacherStatus.WORK
            );

            teacherService.save(teacher);


            long shuttleId = shuttle.getId();
            long academyId = academy.getId();
            long teacherId = teacher.getId();
            long driverId = driver.getId();

            double latitude = 34.123;
            double longitude = 126.123;
            int speed = 14;
            ShuttleLocationHistory shuttleLocationHistory = ShuttleLocationHistory.of(shuttleId,latitude,longitude,speed);
            ShuttleDrive shuttleDrive = ShuttleDrive.of(shuttleId,1,driverId,teacherId);
            ShuttleChildRide shuttleChildRide = ShuttleChildRide.of(shuttleId,1,latitude,longitude);
            ShuttleLocation shuttleLocation = ShuttleLocation.of(shuttleId,latitude,longitude,speed);

            shuttleLocationService.save(shuttleLocation);
            shuttleLocationHistoryService.save(shuttleLocationHistory);
            shuttleDriveService.save(shuttleDrive);
            shuttleChildRideService.save(shuttleChildRide);

            //when
            shuttleFacade.endDrive(shuttleId);
            //then

            assertThrows(ShuttleLocationException.class,
                    () -> shuttleLocationService.findByShuttleId(shuttleId));

            assertThrows(ShuttleLocationHistoryException.class,
                    () -> shuttleLocationHistoryService.findByShuttleId(shuttleId));

            assertThrows(ShuttleDriveException.class,
                    () -> shuttleDriveService.findByShuttleId(shuttleId));

            assertThrows(ShuttleChildRideException.class,
                    () -> shuttleChildRideService.findByShuttleId(shuttleId));





        }

    }

}