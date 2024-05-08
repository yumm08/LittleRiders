package kr.co.littleriders.backend.application.facade;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryFacade {

	List<LocalDateTime> getShuttleDateList(Long shuttleId, Long academyId);
}
