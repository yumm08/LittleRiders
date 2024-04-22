package kr.co.littleriders.backend.domain.child;

import java.time.LocalDate;

import kr.co.littleriders.backend.domain.type.Gender;

public class Child {
	private Long id;

	private Long familyId;

	private String name;

	private LocalDate birthDate;

	private Gender gender;

	private String imagePath;

}
