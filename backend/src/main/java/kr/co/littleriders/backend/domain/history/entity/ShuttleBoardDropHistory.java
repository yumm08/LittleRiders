package kr.co.littleriders.backend.domain.history.entity;


import jakarta.persistence.Id;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document("board_drop_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShuttleBoardDropHistory {

    @Id
    private String documentId;

    private String uuid;
    private ShuttleInBoardDropHistory shuttle;
    private DriverInBoardDropHistory driver;
    private TeacherInBoardDropHistory teacher;
    private BoardInBoardDropHistory board;
    private DropInBoardDropHistory drop;
    private List<LocationInBoardDropHistory> locationList;

    private ShuttleBoardDropHistory(String uuid,
                                    ShuttleInBoardDropHistory shuttleInBoardDropHistory,
                                    DriverInBoardDropHistory driverInBoardDropHistory,
                                    TeacherInBoardDropHistory teacherInBoardDropHistory,
                                    BoardInBoardDropHistory boardInBoardDropHistory,
                                    DropInBoardDropHistory dropInBoardDropHistory,
                                    List<LocationInBoardDropHistory> locationList) {
        this.uuid = uuid;
        this.shuttle = shuttleInBoardDropHistory;
        this.driver = driverInBoardDropHistory;
        this.teacher = teacherInBoardDropHistory;
        this.board = boardInBoardDropHistory;
        this.drop = dropInBoardDropHistory;
        this.locationList = locationList;
    }

    public static ShuttleBoardDropHistory of(String uuid, Shuttle shuttle, Driver driver, Teacher teacher, ShuttleBoard shuttleBoard, ShuttleDrop shuttleDrop, List<ShuttleLocation> shuttleLocationList) {
        ShuttleInBoardDropHistory shuttleInBoardDropHistory = ShuttleInBoardDropHistory.from(shuttle);
        DriverInBoardDropHistory driverInBoardDropHistory = DriverInBoardDropHistory.from(driver);
        TeacherInBoardDropHistory teacherInBoardDropHistory = TeacherInBoardDropHistory.from(teacher);
        BoardInBoardDropHistory boardInBoardDropHistory = BoardInBoardDropHistory.from(shuttleBoard);
        DropInBoardDropHistory dropInBoardDropHistory = DropInBoardDropHistory.from(shuttleDrop);

        List<LocationInBoardDropHistory> locationInBoardDropHistoryList = shuttleLocationList.stream().map(LocationInBoardDropHistory::from)
                .toList();

        return new ShuttleBoardDropHistory(uuid, shuttleInBoardDropHistory, driverInBoardDropHistory, teacherInBoardDropHistory, boardInBoardDropHistory, dropInBoardDropHistory,locationInBoardDropHistoryList);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ShuttleInBoardDropHistory {
        private long id;
        private String name;
        private String type;
        private String licenseNumber;

        private static ShuttleBoardDropHistory.ShuttleInBoardDropHistory from(Shuttle shuttle) {
            long id = shuttle.getId();
            String name = shuttle.getName();
            String type = shuttle.getType();
            String licenseNumber = shuttle.getLicenseNumber();
            return new ShuttleBoardDropHistory.ShuttleInBoardDropHistory(id, name, type, licenseNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class DriverInBoardDropHistory {

        private long id;
        private String name;
        private String phoneNumber;

        private static ShuttleBoardDropHistory.DriverInBoardDropHistory from(Driver driver) {
            long id = driver.getId();
            String name = driver.getName();
            String phoneNumber = driver.getPhoneNumber();
            return new ShuttleBoardDropHistory.DriverInBoardDropHistory(id, name, phoneNumber);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class TeacherInBoardDropHistory {

        private long id;
        private String name;
        private String phoneNumber;

        private static ShuttleBoardDropHistory.TeacherInBoardDropHistory from(Teacher teacher) {
            long id = teacher.getId();
            String name = teacher.getName();
            String phoneNumber = teacher.getPhoneNumber();
            return new ShuttleBoardDropHistory.TeacherInBoardDropHistory(id, name, phoneNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class BoardInBoardDropHistory {

        private double latitude;
        private double longitude;
        private LocalDateTime time;

        private static ShuttleBoardDropHistory.BoardInBoardDropHistory from(ShuttleBoard shuttleBoard) {
            double latitude = shuttleBoard.getLatitude();
            double longitude = shuttleBoard.getLongitude();
            LocalDateTime time = shuttleBoard.getTime();

            return new ShuttleBoardDropHistory.BoardInBoardDropHistory(latitude, longitude, time);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class DropInBoardDropHistory {

        private double latitude;
        private double longitude;
        private LocalDateTime time;

        private static ShuttleBoardDropHistory.DropInBoardDropHistory from(ShuttleDrop shuttleDrop) {
            double latitude = shuttleDrop.getLatitude();
            double longitude = shuttleDrop.getLongitude();
            LocalDateTime time = shuttleDrop.getTime();

            return new ShuttleBoardDropHistory.DropInBoardDropHistory(latitude, longitude, time);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class LocationInBoardDropHistory {

        private double latitude;
        private double longitude;
        private int speed;
        private LocalDateTime time;

        private static LocationInBoardDropHistory from(ShuttleLocation shuttleLocation) {
            double latitude = shuttleLocation.getLatitude();
            double longitude = shuttleLocation.getLongitude();
            int speed = shuttleLocation.getSpeed();
            LocalDateTime time = shuttleLocation.getTime();
            return new LocationInBoardDropHistory(latitude, longitude, speed, time);
        }
    }
}
