package kr.co.littleriders.backend.domain.academy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.family.entity.Family;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "academy_family")
public class AcademyFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "family_id", nullable = false)
	 private Family family;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "academy_id", nullable = false)
	 private Academy academy;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private AcademyFamilyStatus status;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}

