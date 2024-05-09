package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

	private String beconNumber;

	private String familyName;

	private String familyPhoneNumber;

	private AcademyChildDetailResponse(Long academyChildId, String name, LocalDate birthDate, String gender,
		String imagePath, String address, String status, String beconNumber, String familyName, String phoneNumber) {
		this.academyChildId = academyChildId;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.imagePath = imagePath;
		this.address = address;
		this.childStatus = status;
		this.beconNumber = beconNumber;
		this.familyName = familyName;
		this.familyPhoneNumber = phoneNumber;
	}

	public static AcademyChildDetailResponse from(AcademyChild academyChild) {
		return new AcademyChildDetailResponse(academyChild.getId(),
											academyChild.getName(),
											academyChild.getBirthDate(),
											academyChild.getGender().name(),
											academyChild.getImagePath(),
											academyChild.getAddress(),
											academyChild.getStatus().name(),
											academyChild.getBeaconNumber(),
											academyChild.getFamilyName(),
											academyChild.getPhoneNumber());
	}

}
