package kr.co.littleriders.backend.application.facade.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.facade.HistoryFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryFacadeImpl implements HistoryFacade {

	private final ShuttleDriveHistoryService shuttleDriveHistoryService;
	private final ShuttleService shuttleService;
	private final AcademyService academyService;

	@Override
	public List<LocalDateTime> readShuttleDateList(Long shuttleId, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleService.findById(shuttleId);
		if (shuttle.equalsAcademy(academy)) {
			throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
		}

		return shuttleDriveHistoryService.findDistinctYearAndMonthAndDayListByShuttleId(shuttleId);
	}

	@Override
	public List<ShuttleDailyHistoryResponse> readShuttleDailyHistory(Long academyId, Long shuttleId, LocalDate date) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleService.findById(shuttleId);
		if (shuttle.equalsAcademy(academy)) {
			throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
		}
		List<ShuttleDailyHistoryResponse> shuttleDailyHistoryList = shuttleDriveHistoryService.findByShuttleIdAndStartAt(shuttleId,
																								date.getYear(), date.getMonthValue(),
																								date.getDayOfMonth()).stream()
																								.map(ShuttleDailyHistoryResponse::from)
																								.toList();

		return shuttleDailyHistoryList;
	}
}
