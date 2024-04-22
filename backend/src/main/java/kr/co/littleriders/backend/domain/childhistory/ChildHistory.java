package kr.co.littleriders.backend.domain.childhistory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import kr.co.littleriders.backend.domain.type.Gender;

public class ChildHistory {
	private Long id;

	private Long childId;

	private String name;

	private LocalDate birthDate;

	private Gender gender;

	private String imagePath;

	private LocalDateTime createdAt;


}
