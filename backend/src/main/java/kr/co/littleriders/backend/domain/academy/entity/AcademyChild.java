package kr.co.littleriders.backend.domain.academy.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "academy_child")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicUpdate
public class AcademyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 원생 정보 id

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "beacon_number")
    private String beaconNumber; // 카드 정보

    @Column(name = "phone_number")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AcademyChildStatus status; // 상태


    @Column(name = "memo")
    private String memo;


    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "academyChild")
    private List<ChildBoardDropInfo> childBoardDropInfoList; // 원생 승하차 목록


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // 학원


    //TODO- HOTFIX-이윤지 -
    //TODO- HOTFIX-이수현 - null 다른값 처리 필요함

    public static AcademyChild of(Academy academy, AcademyChildStatus status) {

        return null;
    }

    public void updateStatus(AcademyChildStatus status) {
        this.status = status;
    }

    public boolean equalsAcademy(Academy academy) {
        return this.academy.equals(academy);
    }

    public boolean isAttending() {
        return this.status.equals(AcademyChildStatus.ATTENDING);
    }

}