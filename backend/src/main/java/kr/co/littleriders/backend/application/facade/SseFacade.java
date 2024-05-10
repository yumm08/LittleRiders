package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseFacade {

    SseEmitter createSmsUserSseConnectionByUuid(String uuid);

    SseEmitter createAcademySseConnectionByAcademyId(long academyId);

    void broadcastShuttleLocationByShuttleId(long shuttleId, ShuttleLocationRequest shuttleLocationRequest);
}