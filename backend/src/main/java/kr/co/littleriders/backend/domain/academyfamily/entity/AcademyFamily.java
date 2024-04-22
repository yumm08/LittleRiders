package kr.co.littleriders.backend.domain.academyfamily.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AcademyFamily {

	@Id
	private Long id;

	// private Family family;

	// private Academy academy;

	private AcademyFamilyStatus status;

	private LocalDateTime updatedAt;
}

