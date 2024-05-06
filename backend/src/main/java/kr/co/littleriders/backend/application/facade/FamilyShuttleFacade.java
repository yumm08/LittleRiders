package kr.co.littleriders.backend.application.facade;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface FamilyShuttleFacade {
//    ShuttleLocationResponse findShuttleLocationByFamilyIdAndShuttleId(long familyId, long shuttleId);

    SseEmitter subscribeShuttle(long familyId, long shuttleId);
}
