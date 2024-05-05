package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface AcademyShuttleFacade {

	Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Long academyId);

	List<AcademyShuttleResponse> readShuttleList(Long academyId);

    Resource readShuttleImage(Long academyId, Long shuttleId);
}
