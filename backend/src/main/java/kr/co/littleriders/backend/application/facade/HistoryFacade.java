package kr.co.littleriders.backend.application.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;

public interface HistoryFacade {

	List<LocalDateTime> readShuttleDateList(Long shuttleId, Long academyId);

	List<ShuttleDailyHistoryResponse> readShuttleDailyHistory(Long academyId, Long shuttleId, LocalDate date);

}
