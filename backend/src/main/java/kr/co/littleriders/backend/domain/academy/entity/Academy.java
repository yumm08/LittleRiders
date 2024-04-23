package kr.co.littleriders.backend.domain.academy.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "academy")
public class Academy {

    // 학원id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 이메일
    @Column(name = "email", nullable = false)
    private String email;

    // 비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    // 학원이름
    @Column(name = "name", nullable = false)
    private String name;

    // 학원주소
    @Column(name = "address", nullable = false)
    private String address;

    // 학원 전화번호
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // 이미지 경로
    @Column(name = "image_path")
    private String imagePath;

//    // 셔틀 목록
//    @OneToMany(mappedBy = "academy_id")
//    private List<Shuttle> shuttleList;
//
//    // 기사 목록
//    @OneToMany(mappedBy = "academy_id")
//    private List<Driver> driverList;
//
//    // 선생님 목록
//    @OneToMany(mappedBy = "academy_id")
//    private List<Teacher> teacherList;
//
//    // 단말기 목록
//    @OneToMany(mappedBy = "academy_id")
//    private List<Terminal> terminalList;
}
