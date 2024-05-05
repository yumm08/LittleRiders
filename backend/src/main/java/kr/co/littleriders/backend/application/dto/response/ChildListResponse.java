package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDate;

import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChildListResponse {

	private Long childId;

	private String name;

	private LocalDate birthDate;

	private String gender;

	private String image;

	public ChildListResponse(Long id, String name, LocalDate birthDate, String gender, String imagePath) {
		this.childId = id;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.image = imagePath;
	}

	public static ChildListResponse from(Child child) {

		String imagePath = "/api/family/child/" + child.getId() + "/image";

		return new ChildListResponse(child.getId()
									, child.getName()
									, child.getBirthDate()
									, child.getGender().name()
									, imagePath);
	}

}
