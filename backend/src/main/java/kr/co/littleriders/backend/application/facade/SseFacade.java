package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseFacade {


    SseEmitter subscribeShuttle(long shuttleId);

    void broadcastShuttleLocation(long shuttleId, ShuttleLocationRequest shuttleLocationRequest);
}
