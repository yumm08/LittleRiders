package kr.co.littleriders.backend.domain.academychild.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AcademyChild {

	@Id
	private Long id;

	// private Child child;

	// private AcademyFamily academyFamily;

	// private Academy academy;

	private AcademyChildStatus status;

	private String cardType;

	private String cardNumber;

	private LocalDateTime updatedAt;

}
