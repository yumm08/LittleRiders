package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ChildDetailHistoryResponse {

	private Long historyId;

	private String academyName;

	private String childName;

	private String status;

	private LocalDateTime createdAt;

	private Double latitude;

	private Double longitude;

	private ChildDetailHistoryResponse(Long historyId, String academyName, String childName, String status, LocalDateTime createdAt, double latitude, double longitude) {
		this.historyId = historyId;
		this.academyName = academyName;
		this.childName = childName;
		this.status = status;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static ChildDetailHistoryResponse from(BoardDropHistory boardDropHistory) {

		return new ChildDetailHistoryResponse(boardDropHistory.getId()
											, boardDropHistory.getAcademy().getName()
											, boardDropHistory.getAcademyChild().getChild().getName()
											, boardDropHistory.getStatus().name()
											, boardDropHistory.getCreatedAt()
											, boardDropHistory.getLatitude()
											, boardDropHistory.getLongitude());
	}
}
