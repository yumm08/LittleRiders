package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildRegistRequest {

	@NotBlank
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotBlank
	private String gender;

	private MultipartFile image;

	public Child toEntity(Family family) {
		return Child.of(this.name, this.birthDate, Enum.valueOf(Gender.class, this.getGender()), family);
	}

}
