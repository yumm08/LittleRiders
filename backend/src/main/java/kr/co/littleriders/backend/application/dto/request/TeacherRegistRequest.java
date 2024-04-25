package kr.co.littleriders.backend.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegistRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String phoneNumber;

	private MultipartFile image;
}
