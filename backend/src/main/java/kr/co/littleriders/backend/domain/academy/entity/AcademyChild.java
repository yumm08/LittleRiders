package kr.co.littleriders.backend.domain.academy.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "academy_child")
public class AcademyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 원생 정보 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child; // 자녀

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_family_id", nullable = false)
    private AcademyFamily academyFamily; // 보호자 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // 학원

    @OneToMany(mappedBy = "academyChild")
    private List<ChildBoardDropInfo> childBoardDropInfoList; // 원생 승하차 목록

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AcademyChildStatus status; // 상태

    @Column(name = "card_type", nullable = false)
    private CardType cardType; // 카드 종류

    @Column(name = "card_number")
    private String cardNumber; // 카드 정보

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 상태 변경 일자

}