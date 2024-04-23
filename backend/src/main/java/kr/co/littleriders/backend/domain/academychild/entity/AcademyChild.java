package kr.co.littleriders.backend.domain.academychild.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "academy_child")
public class AcademyChild {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// @ManyToOne
	// @JoinColumn(name = "child_id", nullable = false)
	// private Child child;

	// @ManyToOne
	// @JoinColumn(name = "academy_family_id", nullable = false)
	// private AcademyFamily academyFamily;

	// @ManyToOne
	// @JoinColumn(name = "academy_id", nullable = false)
	// private Academy academy;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private AcademyChildStatus status;

	@Column(name = "card_type", nullable = false)
	private String cardType;

	@Column(name = "card_number")
	private String cardNumber;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
