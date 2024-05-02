package kr.co.littleriders.backend.application.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChildBoardHistoryResponse {

	private List<ChildBoardHistory> boardHistoryList;

	private Integer page;

	private Boolean hasNext;

	public static ChildBoardHistoryResponse of(List<ChildBoardHistory> boardHistoryList, int page, boolean hasNext) {
		return new ChildBoardHistoryResponse(boardHistoryList, page, hasNext);
	}
}
