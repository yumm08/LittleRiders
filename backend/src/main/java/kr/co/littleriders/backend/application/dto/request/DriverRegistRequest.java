package kr.co.littleriders.backend.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegistRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String phoneNumber;

	private MultipartFile image;

	public Driver toEntity(Academy academy) {
		return Driver.of(this.name, this.phoneNumber, academy, DriverStatus.WORK);
	}
}
