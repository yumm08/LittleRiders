package kr.co.littleriders.backend.domain.academy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "academy_family")
@NoArgsConstructor
public class AcademyFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 보호자 정보 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family; // 보호자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // 학원

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AcademyFamilyStatus status; // 상태

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 상태 변경 일자

    private AcademyFamily(Family family, Academy academy, AcademyFamilyStatus academyFamilyStatus) {
        this.family = family;
        this.academy = academy;
        this.status = academyFamilyStatus;
    }

    public static AcademyFamily from(Pending pending) {
        return new AcademyFamily(pending.getChild().getFamily()
                                , pending.getAcademy()
                                , AcademyFamilyStatus.AVAIL);
    }
}

