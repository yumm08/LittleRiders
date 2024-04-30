package kr.co.littleriders.backend.application.dto.response;

import java.time.format.DateTimeFormatter;

import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChildBoardHistory {

	private Long histroyId;

	private String academyName;

	private String childName;

	private String status;

	private String createdAt;

	public ChildBoardHistory(Long histroyId, String academyName, String childName, String status, String createdAt) {
		this.histroyId = histroyId;
		this.academyName = academyName;
		this.childName = childName;
		this.status = status;
		this.createdAt = createdAt;
	}

	public static ChildBoardHistory from(BoardDropHistory boardDropHistory) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");
		String formattedDate = boardDropHistory.getCreatedAt().format(formatter);

		return new ChildBoardHistory(boardDropHistory.getId(),
			boardDropHistory.getAcademy().getName(),
			boardDropHistory.getAcademyChild().getChild().getName(),
			boardDropHistory.getStatus().name(), formattedDate);
	}
}
