package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyChildDetailResponse {

	private Long academyChildId;

	private String name;

	private LocalDate birthDate;

	private String gender;

	private String imagePath;

	private String address;

	private String childStatus;

	private String cardType;

	private String cardNumber;

	private String familyName;

	private String familyPhoneNumber;


}
