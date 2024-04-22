package kr.co.littleriders.backend.domain.familyhistory;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FamilyHistory {

	@Id
	private Long id;

	// private Family family;

	private String name;

	private String address;

	private String phoneNumber;

	private LocalDateTime createdAt;
}
