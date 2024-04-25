package kr.co.littleriders.backend.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleRegistRequest {

	@NotBlank
	private String licenseNumber;

	@NotBlank
	private String type;

	@NotBlank
	private String name;

	private MultipartFile image;

}
