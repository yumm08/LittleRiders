package kr.co.littleriders.backend.domain.history.entity;


import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.ChildBoardDropDto;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<ChildBoardDrop> childBoardList;
    private List<ChildBoardDrop> childDropList;

    private String routeName;
    private LocalDateTime start;
    private LocalDateTime end;

    private ShuttleDriveHistory(
            LocalDateTime start,
            LocalDateTime end,
            String routeName,
            ShuttleInShuttleDriveHistory shuttleDriveHistory,
            DriverInShuttleDriveHistory driverInShuttleDriveHistory,
            TeacherInShuttleDriveHistory teacherInShuttleDriveHistory,
            List<ChildBoardDrop> boardList,
            List<ChildBoardDrop> dropList,
            List<LocationInShuttleDriveHistory> locationList
                                ) {
        this.start = start;
        this.end = end;
        this.routeName = routeName;
        this.shuttle = shuttleDriveHistory;
        this.driver = driverInShuttleDriveHistory;
        this.teacher = teacherInShuttleDriveHistory;
        this.childBoardList = boardList;
        this.childDropList = dropList;
        this.locationList = locationList;
    }

   public static ShuttleDriveHistory of(LocalDateTime start, LocalDateTime end, String routeName, Shuttle shuttle, Driver driver, Teacher teacher, List<ChildBoardDropDto> boardList, List<ChildBoardDropDto> dropList, List<ShuttleLocation> shuttleLocationList) {

       ShuttleInShuttleDriveHistory shuttleInShuttleDriveHistory = ShuttleInShuttleDriveHistory.from(shuttle);
       DriverInShuttleDriveHistory driverInShuttleDriveHistory = DriverInShuttleDriveHistory.from(driver);
       TeacherInShuttleDriveHistory teacherInShuttleDriveHistory = TeacherInShuttleDriveHistory.from(teacher);
       List<ChildBoardDrop> childBoardList = boardList.stream().map(ChildBoardDrop::from).collect(Collectors.toList());
       List<ChildBoardDrop> childDropList = dropList.stream().map(ChildBoardDrop::from).collect(Collectors.toList());
       List<LocationInShuttleDriveHistory> locationInShuttleDriveHistoryList = shuttleLocationList.stream().map(LocationInShuttleDriveHistory::from).toList();
       return new ShuttleDriveHistory(start, end, routeName, shuttleInShuttleDriveHistory, driverInShuttleDriveHistory, teacherInShuttleDriveHistory, childBoardList, childDropList, locationInShuttleDriveHistoryList);
   }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShuttleInShuttleDriveHistory {
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
    public static class DriverInShuttleDriveHistory {

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
    public static class TeacherInShuttleDriveHistory {

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
    private static class ChildBoardDrop { // 들어가야 함
        private long id;
        private String name;
        private String address;
        private double latitude;
        private double longitude;
        private LocalDateTime time;

        public static ChildBoardDrop from(ChildBoardDropDto childBoardDropDto) {
            return new ChildBoardDrop();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LocationInShuttleDriveHistory {
        private double latitude;
        private double longitude;
        private int speed;
        private LocalDateTime time;

       private static LocationInShuttleDriveHistory from(ShuttleLocation shuttleLocationList) {//주석처리 - 김도현
           double latitude = shuttleLocationList.getLatitude();
           double longitude = shuttleLocationList.getLongitude();
           int speed = shuttleLocationList.getSpeed();
           LocalDateTime time = shuttleLocationList.getTime();
           return new LocationInShuttleDriveHistory(latitude, longitude, speed, time);
       }
    }


}
