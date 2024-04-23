package kr.co.littleriders.backend.domain.academyfamily.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "academy_family")
public class AcademyFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// @ManyToOne
	// @JoinColumn(name = "family_id", nullable = false)
	// private Family family;

	// @ManyToOne
	// @JoinColumn(name = "academy_id", nullable = false)
	// private Academy academy;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private AcademyFamilyStatus status;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}

