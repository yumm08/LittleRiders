package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDate;
import java.util.List;

import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChildDetailResponse {

	private Long childId;

	private String name;

	private LocalDate birthDate;

	private String gender;

	private String imagePath;

	private String status;

	private List<AcademyList> academyList;

	private ChildDetailResponse(Long childId, String name, LocalDate birthDate, String gender, String imagePath, String status, List<AcademyList> academyList) {
		this.childId = childId;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.imagePath = imagePath;
		this.status = status;
		this.academyList = academyList;
	}

	public static ChildDetailResponse of(Child child, String status, List<AcademyList> academyList) {

		// String imagePath = "/api/family/child/" + child.getId() + "/image";

		return new ChildDetailResponse(child.getId(),
									   child.getName(),
									   child.getBirthDate(),
									   child.getGender().name(),
									   child.getImagePath(),
									   status,
									   academyList);

	}
}
