package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleLocationResponse;
import kr.co.littleriders.backend.application.facade.SseFacade;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
class SseFacadeImpl implements SseFacade {

    private final Map<Long, List<SseEmitter>> subscribeMapByShuttleId = new ConcurrentHashMap<>();
    private static final long RECONNECTION_TIMEOUT = 1000L;


    @Override
    public SseEmitter subscribeShuttle(long shuttleId) {

        SseEmitter emitter = new SseEmitter();
        if (!subscribeMapByShuttleId.containsKey(shuttleId)) {
            subscribeMapByShuttleId.put(shuttleId, new ArrayList<SseEmitter>());
        }
        emitter.onTimeout(emitter::complete);
        emitter.onError(e -> {
            emitter.complete();
        });
        emitter.onCompletion(() -> {
        });
        subscribeMapByShuttleId.get(shuttleId).add(emitter);
        return emitter;
    }


    @Override
    public void broadcastShuttleLocation(long shuttleId, ShuttleLocationRequest shuttleLocationRequest) {

        if(!subscribeMapByShuttleId.containsKey(shuttleId)){
            subscribeMapByShuttleId.put(shuttleId, new ArrayList<SseEmitter>());
        }
        subscribeMapByShuttleId.get(shuttleId).forEach(emitter -> {

            ShuttleLocationResponse shuttleLocationResponse = ShuttleLocationResponse.from(shuttleLocationRequest);
            try {
                emitter.send(SseEmitter.event()
                        .name("location")
                        .id("location")
                        .reconnectTime(RECONNECTION_TIMEOUT)
                        .data(shuttleLocationResponse, MediaType.APPLICATION_JSON));
            } catch (Exception ignored) {
            }
        });
    }

}
