package kr.co.littleriders.backend.application.facade.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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
	public List<LocalDateTime> getShuttleDateList(Long shuttleId, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleService.findById(shuttleId);
		if (shuttle.equalsAcademy(academy)) {
			throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
		}

		return shuttleDriveHistoryService.findDistinctYearAndMonthAndDayListByShuttleId(shuttleId);
	}
}
