package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChildBoardHistory {

	private Long historyId;

	private String academyName;

	private String childName;

	private String status;

	private LocalDateTime createdAt;

	public ChildBoardHistory(Long historyId, String academyName, String childName, String status, LocalDateTime createdAt) {
		this.historyId = historyId;
		this.academyName = academyName;
		this.childName = childName;
		this.status = status;
		this.createdAt = createdAt;
	}

	public static ChildBoardHistory from(BoardDropHistory boardDropHistory) {
		return new ChildBoardHistory(boardDropHistory.getId(),
			boardDropHistory.getAcademy().getName(),
			boardDropHistory.getAcademyChild().getChild().getName(),
			boardDropHistory.getStatus().name(), boardDropHistory.getCreatedAt());
	}
}
