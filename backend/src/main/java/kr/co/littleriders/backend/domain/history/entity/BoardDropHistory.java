package kr.co.littleriders.backend.domain.history.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_drop_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BoardDropHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 히스토리 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_child_id")
    private AcademyChild academyChild; // 원생 정보

    @Column(name = "latitude",nullable = false)
    private double latitude; // 위도

    @Column(name = "longitude",nullable = false)
    private double longitude; // 경도

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BoardDropHistoryStatus status; // 승차/하차

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt; // 탑승 시간

    private BoardDropHistory(Academy academy, AcademyChild academyChild, double latitude, double longitude, BoardDropHistoryStatus boardDropHistoryStatus) {
        this.academy = academy;
        this.academyChild = academyChild;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = boardDropHistoryStatus;
    }

    public static BoardDropHistory of(Academy academy, AcademyChild academyChild, double latitude, double longitude, BoardDropHistoryStatus boardDropHistoryStatus) {
        return new BoardDropHistory(academy, academyChild, latitude, longitude, boardDropHistoryStatus);
    }

    public boolean equalsFamily(Family family) {
        return this.academyChild.getChild().getFamily().equals(family);
    }
}