package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDate;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;
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

	private AcademyChildDetailResponse(Long academyChildId, String name, LocalDate birthDate, String gender,
		String imagePath, String address, String status, String cardType, String cardNumber,
		String familyName, String phoneNumber) {
		this.academyChildId = academyChildId;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.imagePath = imagePath;
		this.address = address;
		this.childStatus = status;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.familyName = familyName;
		this.familyPhoneNumber = phoneNumber;
	}

	public static AcademyChildDetailResponse from(AcademyChild academyChild) {
		return new AcademyChildDetailResponse(academyChild.getId(),
											academyChild.getChild().getName(),
											academyChild.getChild().getBirthDate(),
											academyChild.getChild().getGender().name(),
											academyChild.getChild().getImagePath(),
											academyChild.getAcademyFamily().getFamily().getAddress(),
											academyChild.getStatus().name(),
											academyChild.getCardType().name(),
											academyChild.getCardNumber(),
											academyChild.getAcademyFamily().getFamily().getName(),
											academyChild.getAcademyFamily().getFamily().getPhoneNumber());
	}

	public static AcademyChildDetailResponse to(ChildHistory childHistory, FamilyHistory familyHistory, AcademyChild academyChild) {
		String address = (familyHistory != null) ? familyHistory.getAddress() : academyChild.getAcademyFamily().getFamily().getAddress();
		String familyName = (familyHistory != null) ? familyHistory.getName() : academyChild.getAcademyFamily().getFamily().getName();
		String phoneNumber = (familyHistory != null) ? familyHistory.getPhoneNumber() : academyChild.getAcademyFamily().getFamily().getPhoneNumber();

		return new AcademyChildDetailResponse(academyChild.getId(),
											childHistory.getName(),
											childHistory.getBirthDate(),
											childHistory.getGender().name(),
											childHistory.getImagePath(),
											address,
											academyChild.getStatus().name(),
											academyChild.getCardType().name(),
											academyChild.getCardNumber(),
											familyName,
											phoneNumber);
	}
}
