package kr.co.littleriders.backend.domain.academy.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.global.entity.Member;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "academy")
public class Academy implements Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private Long id; // 학원id

    @Getter
    @Column(name = "email", nullable = false)
    private String email; // 이메일

    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    @Getter
    @Column(name = "name", nullable = false)
    private String name; // 학원이름

    @Getter
    @Column(name = "address", nullable = false)
    private String address; // 학원주소

    @Getter
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 학원 전화번호

    @Getter
    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @OneToMany(mappedBy = "academy")
    private List<Shuttle> shuttleList; // 차량 목록

    @OneToMany(mappedBy = "academy")
    private List<Driver> driverList; // 기사 목록

    @OneToMany(mappedBy = "academy")
    private List<Teacher> teacherList; // 선생님 목록

    @OneToMany(mappedBy = "academy")
    private List<Terminal> terminalList; // 단말기 목록

    @OneToMany(mappedBy = "academy")
    private List<Station> stationList; // 정류장 목록

    @OneToMany(mappedBy = "academy")
    private List<Route> routeList; // 경로 목록

    @OneToMany(mappedBy = "academy")
    private List<BoardDropHistory> boardDropHistoryList; // 원생 탑승 이력 목록

    @OneToMany(mappedBy = "academy")
    private List<AcademyChild> academyChildList; // 원생정보 목록

    @OneToMany(mappedBy = "academy")
    private List<AcademyFamily> academyFamilyList; // 보호자 정보 목록

    @OneToMany(mappedBy = "academy")
    private List<Pending> pendingList; // 원생 승인 신청 목록
}
