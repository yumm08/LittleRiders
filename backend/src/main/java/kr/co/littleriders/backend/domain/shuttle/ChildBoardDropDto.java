package kr.co.littleriders.backend.domain.shuttle;

import java.time.LocalDateTime;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChildBoardDropDto {

	private Long academyChildId;

	private String academyChildName;

	private String address;

	private double latitude;

	private double longitude;

	private LocalDateTime time;

	public static ChildBoardDropDto of(AcademyChild academyChild, double latitude, double longitude, LocalDateTime time) {
		return new ChildBoardDropDto(academyChild.getId(), academyChild.getName(), academyChild.getAddress(), latitude, longitude, time);
	}
}
