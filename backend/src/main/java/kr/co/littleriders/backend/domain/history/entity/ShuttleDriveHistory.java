package kr.co.littleriders.backend.domain.history.entity;


import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.dto.ShuttleLocationDTO;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("shuttle_drive_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShuttleDriveHistory {

    @Id
    private String documentId;

    private ShuttleInShuttleDriveHistory shuttle;
    private DriverInShuttleDriveHistory driver;
    private TeacherInShuttleDriveHistory teacher;
    private List<LocationInShuttleDriveHistory> locationList;

    private LocalDateTime start;
    private LocalDateTime end;

    private ShuttleDriveHistory(
            LocalDateTime start,
            LocalDateTime end,
            ShuttleInShuttleDriveHistory shuttleDriveHistory,
                                DriverInShuttleDriveHistory driverInShuttleDriveHistory,
                                TeacherInShuttleDriveHistory teacherInShuttleDriveHistory,
                                List<LocationInShuttleDriveHistory> locationList
                                ) {
        this.shuttle = shuttleDriveHistory;
        this.driver = driverInShuttleDriveHistory;
        this.teacher = teacherInShuttleDriveHistory;
        this.locationList = locationList;
        this.start = start;
        this.end = end;
    }

    public static ShuttleDriveHistory of(LocalDateTime start, LocalDateTime end,Shuttle shuttle, Driver driver, Teacher teacher, List<ShuttleLocationDTO> shuttleLocationDTOList) {

        ShuttleInShuttleDriveHistory shuttleInShuttleDriveHistory = ShuttleInShuttleDriveHistory.from(shuttle);
        DriverInShuttleDriveHistory driverInShuttleDriveHistory = DriverInShuttleDriveHistory.from(driver);
        TeacherInShuttleDriveHistory teacherInShuttleDriveHistory = TeacherInShuttleDriveHistory.from(teacher);
        List<LocationInShuttleDriveHistory> locationInShuttleDriveHistoryList = shuttleLocationDTOList.stream().map(LocationInShuttleDriveHistory::from).toList();
        return new ShuttleDriveHistory(start,end,shuttleInShuttleDriveHistory, driverInShuttleDriveHistory, teacherInShuttleDriveHistory, locationInShuttleDriveHistoryList);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ShuttleInShuttleDriveHistory {
        private long id;
        private String name;
        private String type;
        private String licenseNumber;

        private static ShuttleInShuttleDriveHistory from(Shuttle shuttle) {
            long id = shuttle.getId();
            String name = shuttle.getName();
            String type = shuttle.getType();
            String licenseNumber = shuttle.getLicenseNumber();
            return new ShuttleInShuttleDriveHistory(id, name, type, licenseNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class DriverInShuttleDriveHistory {

        private long id;
        private String name;
        private String phoneNumber;

        private static DriverInShuttleDriveHistory from(Driver driver) {
            long id = driver.getId();
            String name = driver.getName();
            String phoneNumber = driver.getPhoneNumber();
            return new DriverInShuttleDriveHistory(id, name, phoneNumber);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class TeacherInShuttleDriveHistory {

        private long id;
        private String name;
        private String phoneNumber;

        private static TeacherInShuttleDriveHistory from(Teacher teacher) {
            long id = teacher.getId();
            String name = teacher.getName();
            String phoneNumber = teacher.getPhoneNumber();
            return new TeacherInShuttleDriveHistory(id, name, phoneNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ChildBoard {
        private long childId;
        private long academyChildId;
        private String name;
        private String address;
        private double latitude;
        private double longitude;
        private LocalDateTime time;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    private static class LocationInShuttleDriveHistory {
        private double latitude;
        private double longitude;
        private LocalDateTime time;

        private static LocationInShuttleDriveHistory from(ShuttleLocationDTO shuttleLocationDTO) {
            double latitude = shuttleLocationDTO.getLatitude();
            double longitude = shuttleLocationDTO.getLongitude();
            LocalDateTime time = shuttleLocationDTO.getTime();
            return new LocationInShuttleDriveHistory(latitude, longitude, time);
        }
    }


}
