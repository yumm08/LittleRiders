package kr.co.littleriders.backend.domain.child.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.co.littleriders.backend.domain.Gender;

@Entity
public class Child {

	@Id
	private Long id;

	// private Family family;

	private String name;

	private LocalDate birthDate;

	private Gender gender;

	private String imagePath;

}
